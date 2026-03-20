import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

@Path("/products")
public class ProductResource {

    private static Map<Integer, String> products = new HashMap<>();

    static {
        products.put(1, "Laptop");
        products.put(2, "Smartphone");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) {
        String product = products.get(id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }
        return Response.ok(product).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Map<String, String> product) {
        int id = products.size() + 1;
        products.put(id, product.get("name"));
        return Response.status(Response.Status.CREATED).entity("Product added with ID: " + id).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, Map<String, String> product) {
        if (!products.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }
        products.put(id, product.get("name"));
        return Response.ok("Product updated").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id) {
        if (products.remove(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
        }
        return Response.ok("Product deleted").build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getOperation() {
        return Response.ok("GET").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postOperation(String data) {
        if (data != null && !data.isEmpty()) {
            return Response.ok("POST").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Données manquantes").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putOperation(String data) {
        if (data != null && !data.isEmpty()) {
            return Response.ok("PUT").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Aucune donnée transmise").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOperation(@PathParam("id") String id) {
        if (id != null && !id.isEmpty()) {
            return Response.ok("DELETE").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Donnée manquante pour suppression").build();
    }
}