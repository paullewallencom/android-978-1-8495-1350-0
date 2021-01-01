package com.example.aatg.myfirstproject.provider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;


public class MyProvider extends ContentProvider {

	public static final String AUTHORITY = "com.example.aatg.myfirstproject.provider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

	private static final String DB_NAME = "dummy.db";
	private static final int DB_VERSION = 1;
	private static final int DUMMY = 1;

	private DatabaseHelper mDbHelper;

	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static HashMap<String, String> sDummyProjectionMap = new HashMap<String, String>();

	static {
		sUriMatcher.addURI(AUTHORITY, "*", DUMMY);
		sDummyProjectionMap.put("_id", "_id");
		sDummyProjectionMap.put("name", "name");
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();

		final int count;

		switch ( sUriMatcher.match(uri) ) {
		case DUMMY:
			count = db.delete("dummy", where, whereArgs);
			break;

		default:
			throw new IllegalArgumentException("Unkonw URI " + uri);
		}

		return count;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		final Context context = getContext();
		mDbHelper = new DatabaseHelper(context);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		final SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		switch ( sUriMatcher.match(uri) ) {
		case DUMMY:
			qb.setTables("dummy");
			qb.setProjectionMap(sDummyProjectionMap);
			break;

		default:
			throw new IllegalArgumentException("Unkonw URI " + uri);
		}

		final SQLiteDatabase db = mDbHelper.getReadableDatabase();
		final Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE dummy (" +
					"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
					"name VARCHAR(255)" +
					")");

			db.execSQL("INSERT INTO dummy VALUES (1, 'Donald')");
			db.execSQL("INSERT INTO dummy VALUES (2, 'Mickey')");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}

}
