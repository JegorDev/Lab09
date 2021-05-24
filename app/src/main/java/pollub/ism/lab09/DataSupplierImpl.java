package pollub.ism.lab09;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DataSupplierImpl implements ImagesAdapter.DataSupplier {

    ArrayList<ImagesAdapter.AdapterData> adapterData=new ArrayList<>();

    @Override
    public ArrayList<ImagesAdapter.AdapterData> getData() {
        return adapterData;
    }

    public DataSupplierImpl(Resources resources){
        String[] names=resources.getStringArray(R.array.names);
        String[] descriptions=resources.getStringArray(R.array.descriptions);
        TypedArray images=resources.obtainTypedArray(R.array.fruits);
        for(int i=0;i<images.length();i++){
            Bitmap bitmap= BitmapFactory.decodeResource(resources,images.getResourceId(i,0));
            adapterData.add(new ImagesAdapter.AdapterData(names[i],descriptions[i],bitmap));
        }
    }
}
