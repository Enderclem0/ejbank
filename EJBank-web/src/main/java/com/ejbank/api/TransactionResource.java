package com.ejbank.api;

import com.ejbank.api.payload.transaction.*;
import com.ejbank.entities.Transaction;
import com.ejbank.ejbs.transaction.TransactionServiceEjb;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TransactionResource {
    @EJB
    private TransactionServiceEjb transactionService;

// Dans TransactionResource.java

    @GET
    @Path("/list/{account_id}/{offset}/{user_id}")
    public TransactionPayloadList listTransaction(
            @PathParam("account_id") Long accountId,
            @PathParam("offset") Integer offset,
            @PathParam("user_id") Long userId
    ) {
        try {
            // Appeler le service pour récupérer la liste des Entités JPA
            List<Transaction> transactionEntities = transactionService.getListForAccount(accountId, offset);
            // Convertir la liste d'Entités JPA en liste de Payloads d'API
            List<TransactionPayload> payloads = transactionEntities.stream()
                    .map(this::toPayload)
                    .collect(Collectors.toList());
            // Récupérer le nombre total de transactions
            int total = transactionService.getCountForAccount(accountId);
            return new TransactionPayloadList(payloads, total, "");

        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des transactions: " + e.getMessage());
            // Retourner une liste vide
            return new TransactionPayloadList(List.of(), 0, e.getMessage());
        }
    }

    // Méthode de conversion
    private TransactionPayload toPayload(Transaction entity) {
        TransactionPayload payload = new TransactionPayload(
            entity.getId().intValue(),
            entity.getDate(),
            entity.getSource(),
            entity.getDestination(),
            null, // destinationUser n'est pas dans l'Entité Transaction
            entity.getAmount(),
            entity.getAuthor(),
            entity.getComment(),
            entity.getApplied() ? TransactionPayload.State.APPLYED : TransactionPayload.State.WAITING_APPROVE // Logique de conversion d'état
        );
        return payload;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/preview")
    public TransactionPreviewPayload previewTransaction(TransactionBasicPayload transactionBasicPayload) {
        try {
            // Appeler la méthode de prévisualisation dans le service
            return transactionService.preview(
                transactionBasicPayload.getAuthor(), // Assurez-vous que les getters existent et retournent Long
                transactionBasicPayload.getAmount()
            );
        } catch (Exception e) {
            // Gérer les erreurs (ex: compte non trouvé)
            return new TransactionPreviewPayload(
                false,
                transactionBasicPayload.getAmount(),
                transactionBasicPayload.getAmount(),
                "Erreur lors de la prévisualisation.",
                e
            );
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/apply")
    public TransactionPayloadSubmission applyTransaction(TransactionSubmissionBasicPayload transactionBasicPayload) {
        try {
            // Appeler la méthode d'application dans le service
            return transactionService.apply(transactionBasicPayload);
        } catch (Exception e) {
            // Gérer les erreurs (y compris celles levées par le service)
            return new TransactionPayloadSubmission("Erreur lors de l'application: " + e.getMessage(), false);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/validation")
    public TransactionPayloadValidation validationTransaction(TransactionValidationPayload transactionValidationPayload) {
        Long transactionId = transactionValidationPayload.getTransactionId(); // Si c'est le champ ID
        Boolean approve = transactionValidationPayload.getApprove();
        Long authorId = transactionValidationPayload.getAuthor();

        try {
            boolean success = transactionService.transactionValidationPayload(transactionId, approve, authorId);

            if (success) {
                return new TransactionPayloadValidation("Transaction validation successful.", true, null);
            } else {
                return new TransactionPayloadValidation("Validation failed, transaction not found or state incorrect.", false, null);
            }
        } catch (Exception e) {
            return new TransactionPayloadValidation("Internal Server Error: " + e.getMessage(), false, e);
        }
    }

    @GET
    @Path("/validation/notification/{user_id}")
    public Integer notificationTransaction(@PathParam("user_id") Long user_id) {
        try {
            int pendingCount = transactionService.countPendingTransactionsForUser(user_id);
            return pendingCount;
        } catch (Exception e) {
            System.err.println("Erreur lors du comptage des notifications: " + e.getMessage());
            return 0; // Retourner 0 en cas d'erreur
        }
    }
}
