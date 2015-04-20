package vacvin.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pc007 on 2015/4/20.
 */
public class DB {
    private static final String DATABASE_NAME = "history.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "history";

    private Context mContext = null;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public static final String KEY_ROWID = "_id";
    public static final String KEY_height = "height";
    public static final String KEY_weight = "weight";
    public static final String KEY_BMI = "BMI";
    public static final String KEY_CREATED = "created";

    public DB(Context context){
        this.mContext = context;
    }

    public DB open()  {
        dbHelper = new DatabaseHelper(mContext);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private  static class DatabaseHelper extends SQLiteOpenHelper{
        private static final String DATABASE_CREATE =
                "CREATE TABLE history ("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"height INTEGER NOT NULL,"
                +"weight INTEGER NOT NULL,"
                +"BMI INTEGER NOT NULL,"
                +"created TIMESTAMP );";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public Cursor getAll() {
        return db.query(DATABASE_TABLE, //Which table to Select
//	         strCols,// Which columns to return
                new String[] {KEY_ROWID, KEY_height, KEY_weight, KEY_BMI, KEY_CREATED},
                null, // WHERE clause
                null, // WHERE arguments
                null, // GROUP BY clause
                null, // HAVING clause
                KEY_CREATED + " DESC" //Order-by clause
        );
    }

    // add an entry
    public long create(String height,String weight,String BMI) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
        Date now = new Date();
        ContentValues args = new ContentValues();
        args.put(KEY_height, height);
        args.put(KEY_weight, weight);
        args.put(KEY_BMI, BMI);
        args.put(KEY_CREATED, df.format(now.getTime()));

        return db.insert(DATABASE_TABLE, null, args);
    }

    //remove an entry
    public boolean delete(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //query single entry
    public Cursor get(long rowId) throws SQLException {
        Cursor mCursor = db.query(true,
                DATABASE_TABLE,
                new String[] {KEY_ROWID, KEY_height, KEY_weight, KEY_BMI, KEY_CREATED},
                KEY_ROWID + "=" + rowId,
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //update
    public boolean update(long rowId, String height,String weight,String BMI) {
        Date now = new Date();
        ContentValues args = new ContentValues();
        args.put(KEY_height, height);
        args.put(KEY_weight, weight);
        args.put(KEY_BMI, BMI);

        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
