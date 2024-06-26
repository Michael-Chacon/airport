package org.vuelosGlobales.systemAdministrator.document.adapter.in;

import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.document.application.DocumentService;
import org.vuelosGlobales.systemAdministrator.document.domain.Document;

import java.util.List;

public class DocumentConsoleAdapter {
    private final DocumentService documentService;
    Console console = new Console();

    public DocumentConsoleAdapter(DocumentService documentService) {
        this.documentService = documentService;
    }

    public  void crudCountry(){
        menuDocument: while (true){
            System.out.println("1. Registrar documento");
            System.out.println("2. Actualizar info de un documento");
            System.out.println("3. Buscar tipo de documento por ID");
            System.out.println("4. Eliminar un tipo de documento");
            System.out.println("5. Listar todos los tipos de documentos");
            System.out.println("6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar un tipo de documento", "*");
                    String name = console.stringNotNull("Nombre del tipo de documento: ");
                    documentService.createDocument(new Document(name));
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de un tipo de documento", "*");
                    showDocuments();
                    Document documentSelect = Helpers.transformAndValidateObj(
                            () -> documentService.getDocumentById(console.readInt("Seleccione el tipo de documento por el id: "))
                    );
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos del tipo de documento " + documentSelect.getName(), "*");
                    String newName = console.stringNotNull("Nuevo nombre del tipo de documento: ");
                    documentSelect.setName(newName);
                    documentService.updateDocument(documentSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de un documento", "*");
                    System.out.println();
                    showDocuments();
                    Document showDocument = Helpers.transformAndValidateObj(
                            () -> documentService.getDocumentById(console.readInt("Seleccione el documento por el id: "))
                    );
                    System.out.println(showDocument);
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un tipo de documento", "*");
                    showDocuments();
                    Document showDocumentF = Helpers.transformAndValidateObj(
                            () -> documentService.getDocumentById(console.readInt("Seleccione el tipo de documento por el id: "))
                    );
                    int documentDelete = showDocumentF.getId();
                    documentService.deleteDocument(documentDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Documento eliminado con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Documentos Registrados", "*");
                    showDocuments();
                    CuadroDeTexto.dibujarCuadroDeTexto("Fin", null);
                    break;
                case 6:
                    break menuDocument;
            }
            String option = console.stringNull("Algo: ");
        }
    }

    public void showDocuments(){
        List<Document> documents = documentService.getAllDocuments();
        System.out.println("Documentos registrados:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.printf("\n| %-4s | %-16s |%n", "ID", "NOMBRE");
        documents.forEach(document -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.printf("\n| %-4s | %-16s |%n", document.getId(), document.getName());
        });
        System.out.println();
    }
}
