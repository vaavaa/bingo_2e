package regto.kz.bingo_2e.controller;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController extends SQLiteOpenHelper {

    public static final int STATE_OPENED = 0;
    public static final int STATE_CLOSED = 1;
    public static final int STATE_NOTACTIVATED = -1;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bingo_2e_db";

    private SQLiteDatabase db; //Update access
    private SQLiteDatabase dbr; //ReadA access
    private int state;

    public DBController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        state = STATE_NOTACTIVATED;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL("CREATE_TABLE_Game");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS  _Game");
        // create new tables
        onCreate(db);
    }

    public boolean openDB() {
        boolean bRet;
        try {
            db = this.getWritableDatabase();
            dbr = this.getReadableDatabase();
            state = STATE_OPENED;
            bRet = true;
        } catch (SQLException ex) {
            bRet = false;
        }
        return bRet;
    }

    public void close() {
        db.close();
        state = STATE_CLOSED;
    }

    public int getDBState() {
        return state;
    }


}
