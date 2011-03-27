
package rewired.state.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageProvider extends ContentProvider {

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        File file = new File("/mnt/sdcard");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new FileNotFoundException("can't open tmp file");
        }
        FileOutputStream out = new FileOutputStream(file);
        int[] colors = new int[250 * 120];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = 6666;
        }
        Bitmap bitmap = Bitmap.createBitmap(colors, 250, 120, Bitmap.Config.RGB_565);
        bitmap.compress(CompressFormat.PNG, 100, out);
        return ParcelFileDescriptor.open(file, 0);
    }

}
