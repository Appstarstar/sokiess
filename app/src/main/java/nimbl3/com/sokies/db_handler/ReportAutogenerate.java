package nimbl3.com.sokies.db_handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yeshu on 5/4/2017.
 */
public class ReportAutogenerate extends SQLiteOpenHelper {
    final static String name = "db_account";
    final static int version = 1;
    final static String TABLE_NAME_ACCOUNT = "account2";
    final static String CUSTOMER_ID = "customer_id";
    final static String CUSTOMER_DETAILS = "customer_account_string";
    final static String DROP_TABLE_ACCOUNT = "DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT;
    final static String DELETE_TABLE_ACCOUNT = "DELETE FROM " + TABLE_NAME_ACCOUNT;
    final static String SELECT_VALUE_SELECT = "select ";
    final static String SELECT_VALUE_FROM = "from ";
    Cursor cursor;

    final static String CUSTOMER_FIRST_NAME = "customer_first_name";
    final static String CUSTOMER_LAST_NAME = "customer_last_name";
    final static String CUSTOMER_EMAIL_ID = "customer_e_mail";
    final static String CUSTOMER_TELEPHONE = "customer_phone_number";
    final static String CUSTOMER_FAX = "customer_fax";
    final static String CREATE_ACCOUNT_NEW = "create table " + TABLE_NAME_ACCOUNT + "(" + CUSTOMER_ID + " integer primary key," + CUSTOMER_FIRST_NAME + " text,"+ CUSTOMER_LAST_NAME + " text,"+ CUSTOMER_EMAIL_ID + " text,"+ CUSTOMER_TELEPHONE + " text,"+ CUSTOMER_FAX + " text," + CUSTOMER_DETAILS + " text);";

    private static ReportAutogenerate sInstance;

    public static synchronized ReportAutogenerate getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ReportAutogenerate(context.getApplicationContext());
        }
        return sInstance;
    }

    public ReportAutogenerate(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DROP_TABLE_ACCOUNT);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
    public int get_customer_id() {
        String query = SELECT_VALUE_SELECT + CUSTOMER_ID + " " + SELECT_VALUE_FROM + TABLE_NAME_ACCOUNT ;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int result = cursor.getInt(cursor.getColumnIndex(CUSTOMER_ID));
                if (result != 0) {
                    return result;
                }
            }
            cursor.close();
        }
        return 0;
    }
}
