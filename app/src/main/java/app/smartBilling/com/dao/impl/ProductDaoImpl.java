package app.smartBilling.com.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import app.smartBilling.com.dao.ProductDao;
import app.smartBilling.com.helper.SQLDaoHelper;
import app.smartBilling.com.model.Product;

/**
 * Created by vikas on 31/07/15.
 */
public class ProductDaoImpl extends SQLDaoHelper implements ProductDao {

    public ProductDaoImpl(Context context) {
        super(context);
    }

    @Override
    public void save(Product product) {
        db.insert("PRODUCT", null, getContentValues(product));
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<Product>();
        String[] tableColumns = new String[] {"uuid","title","barcode","price"};
        Cursor cursor = db.query("PRODUCT", tableColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Product product = new Product();
            product.setUuid(cursor.getString(0));
            product.setTitle(cursor.getString(1));
            product.setBarcode(cursor.getString(2));
            product.setPrice(cursor.getString(3));
            products.add(product);
            cursor.moveToNext();
        }

        return products;
    }

    @Override
    public Product findProductByBarCode(String barcode) {
        Product product = new Product();
        String[] tableColumns = new String[] {"uuid","title","price"};
        String whereClause = "barcode = ?";
        String[] whereArgs = new String[] {barcode};
        Cursor cursor = db.query("PRODUCT", tableColumns, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product.setUuid(cursor.getString(0));
            product.setTitle(cursor.getString(1));
            product.setPrice(cursor.getString(2));
            cursor.moveToNext();
        }
        if(product.getUuid()!=null) {
            return product;
        }
        return null;
    }

    @Override
    public Product findProductByUUID(String productId) {
        Product product = new Product();
        String[] tableColumns = new String[] {"uuid","title","price"};
        String whereClause = "uuid = ?";
        String[] whereArgs = new String[] {productId};
        Cursor cursor = db.query("PRODUCT", tableColumns, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product.setUuid(cursor.getString(0));
            product.setTitle(cursor.getString(1));
            product.setPrice(cursor.getString(2));
            cursor.moveToNext();
        }
        if(product.getUuid()!=null) {
            return product;
        }
        return null;
    }


    private ContentValues getContentValues(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put("uuid", product.getUuid());
        contentValues.put("title", product.getTitle());
        contentValues.put("barcode", product.getBarcode());
        contentValues.put("price", product.getPrice());
        return contentValues;
    }
}
