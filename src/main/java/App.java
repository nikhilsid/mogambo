import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import configuration.DbConfiguration;
import configuration.MogamboConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by nilesh.m on 13/06/15.
 */
public class App extends Application<MogamboConfiguration>{
    public static void main(String[] args) {
        try {
            new App().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(Bootstrap<MogamboConfiguration> bootstrap) {

    }

    @Override
    public void run(MogamboConfiguration mogamboConfiguration, Environment environment) throws Exception {

    }

    public Mongo initialiseDB(DbConfiguration dbConfig) throws UnknownHostException {
        Mongo mongo=new MongoClient(new ServerAddress(dbConfig.getHost(),dbConfig.getPort()));
        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore((MongoClient)mongo, dbConfig.getDBName());
        initializeDAO(ds);
        return mongo;
    }

    private void initializeDAO(Datastore ds) {
    }
}
