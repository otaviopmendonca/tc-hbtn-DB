import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class UsuarioOperations {

    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();
        MongoCollection<Document> collection = connection.getDatabase().getCollection("usuarios");

        // Inserindo 3 registros
        System.out.println("Inserindo registros...");
        collection.insertOne(new Usuario("Alice", 25).toDocument());
        collection.insertOne(new Usuario("Bob", 30).toDocument());
        collection.insertOne(new Usuario("Charlie", 35).toDocument());

        // Consultando os registros
        System.out.println("Consultando registros...");
        consultarRegistros(collection);

        // Alterando um registro
        System.out.println("Alterando registro...");
        collection.updateOne(eq("nome", "Bob"), new Document("$set", new Document("idade", 32)));

        // Consultando novamente os registros
        System.out.println("Consultando registros após alteração...");
        consultarRegistros(collection);

        // Apagando um registro
        System.out.println("Apagando registro...");
        collection.deleteOne(eq("nome", "Charlie"));

        // Consultando novamente os registros
        System.out.println("Consultando registros após exclusão...");
        consultarRegistros(collection);

        connection.closeConnection();
    }

    private static void consultarRegistros(MongoCollection<Document> collection) {
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Usuario usuario = Usuario.fromDocument(document);
                System.out.println(usuario);
            }
        }
    }
}

