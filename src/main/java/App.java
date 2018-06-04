import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oEntryDao;
import models.Entry;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/addressbook.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oEntryDao entryDao = new Sql2oEntryDao(sql2o);

        get("/entries/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            entryDao.clearAllEntries();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Entry> allEntries = entryDao.getAll();
            model.put("entries", allEntries);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/entries/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "addcontact-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/entries/add", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String mailing = request.queryParams("mailing");
            String email = request.queryParams("email");
            Entry newEntry = new Entry(name, phone, mailing, email);
            entryDao.add(newEntry);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/entries/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEntryToFind = Integer.parseInt(request.params("id"));
            Entry foundEntry = entryDao.findById(idOfEntryToFind);
            model.put("entry", foundEntry);
            return new ModelAndView(model, "entry-detail.hbs");
        }, new HandlebarsTemplateEngine());


        get("/entries/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEntryToFind = Integer.parseInt(request.params("id"));
            entryDao.deleteById(idOfEntryToFind);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/entries/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEntryToFind = Integer.parseInt(request.params("id"));
            Entry foundEntry = entryDao.findById(idOfEntryToFind);
            model.put("entry", foundEntry);
            return new ModelAndView(model, "addcontact-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/entries/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEntryToFind = Integer.parseInt(request.params("id"));
            String name = request.queryParams("name");
            String phone = request.queryParams("phone");
            String mailing = request.queryParams("mailing");
            String email = request.queryParams("email");
            entryDao.update(idOfEntryToFind, name, phone, mailing, email);
            response.redirect("/entries/" + idOfEntryToFind);
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
