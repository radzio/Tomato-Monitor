package model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Radek Piekarz
 * Date: 09.07.12
 * Time: 17:41
 */

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
    // name of the database file for your application -- change to something
    // appropriate for your app
    private static final String DATABASE_NAME = "tomatomonitor.db";
    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access tables
    private Dao<Router, Integer> routerDao = null;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("DatabaseHelper", "onCreate");

    }

    /**
     * This is called when the database is first created. Usually you should
     * call createTable statements here to create the tables that will store
     * your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource)
    {
        try
        {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Router.class);

        } catch (SQLException e)
        {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }


    public Dao<Router, Integer> getRouterDao()
    {
        if (routerDao == null)
        {
            try
            {
                routerDao = getDao(Router.class);
            }
            catch (SQLException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return routerDao;
    }

    /**
     * This is called when your application is upgraded and it has a higher
     * version number. This allows you to adjust the various data to match the
     * new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion)
    {
        try
        {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Router.class, true);
            onCreate(db, connectionSource);
        }
        catch (SQLException e)
        {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close()
    {
        super.close();
    }
}
