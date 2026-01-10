package com.ejbank.ejbs.transaction;

import com.ejbank.payloads.transaction.TransactionPayloadSubmissionDTO;
import com.ejbank.payloads.transaction.TransactionPreviewPayloadDTO;
import com.ejbank.payloads.transaction.TransactionSubmissionBasicPayloadDTO;
import com.ejbank.entities.Transaction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class TransactionServiceEjb implements TransactionService {

    @PersistenceContext
    private EntityManager em;

    // =========================================================================
    // 1. Liste et Pagination
    // =========================================================================

    /**
     * Récupère une liste de transactions pour un compte donné, avec pagination.
     */
    @Override
    public List<Transaction> getListForAccount(Long accountId, Integer offset) {
        // CORRECTION JPQL : Utilisation des champs d'Entité (t.source et t.destination), PAS des noms de colonnes SQL.
        var jpql = "SELECT t FROM Transaction t WHERE t.source = :accountId OR t.destination = :accountId ORDER BY t.date DESC";
        TypedQuery<Transaction> query = em.createQuery(jpql, Transaction.class);

        query.setParameter("accountId", accountId);
        query.setFirstResult(offset);

        return query.getResultList();
    }

    /**
     * Retourne le nombre total de transactions pour un compte donné (pour la pagination).
     */
    public int getCountForAccount(Long accountId) {
        var jpql = "SELECT COUNT(t) FROM Transaction t WHERE t.source = :accountId OR t.destination = :accountId";
        // Utiliser getSingleResult() car COUNT(*) renvoie un seul nombre
        return Math.toIntExact(em.createQuery(jpql, Long.class)
                .setParameter("accountId", accountId)
                .getSingleResult());
    }

    // =========================================================================
    // 2. Validation et Notification
    // =========================================================================

    /**
     * Valide ou rejette une transaction en attente.
     */
    public boolean transactionValidationPayload(Long transactionId, Boolean approve, Long authorId) {

        Transaction transaction = em.find(Transaction.class, transactionId);

        if (transaction == null || transaction.getApplied() != null) {
            // Refuser si elle n'existe pas, ou si elle a déjà été appliquée/refusée (applied n'est pas null)
            return false;
        }

        // 3. Appliquer la décision
        if (approve) {
            transaction.setApplied(true); // Approuvé
            // NOTE : La logique de DÉBIT/CRÉDIT des comptes doit être ajoutée ici !
        } else {
            transaction.setApplied(false); // Rejeté
        }

        // Enregistrer l'auteur de la validation (l'auteur devient le conseiller qui a validé)
        transaction.setAuthor(authorId);

        em.merge(transaction);
        return true;
    }

    /**
     * Retourne le nombre de transactions en attente de validation.
     * Pour ce modèle (où `applied` est NULL tant qu'il n'est pas traité).
     */
    public int countPendingTransactionsForUser(Long userId) {
        // On suppose qu'une transaction est en attente si le champ 'applied' est NULL.
        // Si la validation est assignée à un conseiller spécifique, la requête serait plus complexe.
        var jpql = "SELECT COUNT(t) FROM Transaction t WHERE t.applied IS NULL";

        // Si vous voulez filtrer les transactions en attente D'ÊTRE VALIDÉES PAR cet utilisateur (userId)
        // var jpql = "SELECT COUNT(t) FROM Transaction t WHERE t.applied IS NULL AND t.assignedValidator = :userId";

        return em.createQuery(jpql, Long.class)
                .getSingleResult()
                .intValue();
    }
    /**
     * Calcule les frais et vérifie la faisabilité d'une transaction.
     */
    public TransactionPreviewPayloadDTO preview(Long sourceAccountId, BigDecimal amount) {
        BigDecimal currentBalance = new BigDecimal(10000L);//em.find(Account.class, sourceAccountId).getBalance();

        boolean isFeasible = currentBalance.compareTo(amount) != -1;

        BigDecimal newBalance = isFeasible ? currentBalance.subtract(amount) : currentBalance;

        return new TransactionPreviewPayloadDTO(
                isFeasible,
                currentBalance,
                newBalance,
                "Calcul OK", // message
                null // error
        );
    }
    // Dans TransactionService.java (Nouvelle méthode)

    public TransactionPayloadSubmissionDTO apply(TransactionSubmissionBasicPayloadDTO payload) throws Exception {

        Long sourceId = payload.getSource();
        Long destinationId = payload.getDestination();
        BigDecimal amount = payload.getAmount();
        Long authorId = payload.getAuthor();
        String comment = payload.getComment();

        // 1. Récupérer les données de prévisualisation (pour vérifier à nouveau la faisabilité)
        TransactionPreviewPayloadDTO preview = this.preview(sourceId, amount);

        if (!preview.getResult()) {
            // La transaction n'est plus faisable (solde insuffisant ou erreur)
            return new TransactionPayloadSubmissionDTO(preview.getMessage(), false);
        }

        // 2. Logique de création de l'Entité Transaction
        Transaction newTransaction = new Transaction(
                new java.sql.Date(System.currentTimeMillis()),
                sourceId,
                destinationId,
                amount,
                authorId,
                comment,
                true // Mettre à 'true' si l'application est immédiate (sans étape de validation)
        );

        // Utilisation de em.persist() car nous créons une nouvelle Entité
        em.persist(newTransaction);

        // 3. Logique de mise à jour des soldes (Nécessite l'Entité Account)

        // Account source = em.find(Account.class, sourceId);
        // Account destination = em.find(Account.class, destinationId);

        // // Débit
        // source.setBalance(source.getBalance() - preview.getAmountToDebit());
        // // Crédit
        // destination.setBalance(destination.getBalance() + amount);

        // em.merge(source);
        // em.merge(destination);

        // Retourner le succès. Le conteneur EJB committera la transaction.
        return new TransactionPayloadSubmissionDTO("Transaction appliquée avec succès.", true);
    }
}