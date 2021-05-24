package pollub.ism.lab09;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesHolder> {

    private final Context context;
    private final DataSupplier dataSupplier;
    private LayoutInflater layoutInflater;

    public ImagesAdapter(Context context,DataSupplier dataSupplier){
        this.context=context;
        this.dataSupplier=dataSupplier;
        this.layoutInflater=LayoutInflater.from(context);
    }

    public static class AdapterData{
        public final String name;
        public final String description;
        public final Bitmap bitmap;

        public AdapterData(String name,String description,Bitmap bitmap){
            this.name=name;
            this.description=description;
            this.bitmap=bitmap;
        }
    }

    public interface DataSupplier{
        ArrayList<AdapterData> getData();
    }

    class ImagesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImagesAdapter imagesAdapter;
        public final TextView namesList;
        public final ImageView imagesList;

        public ImagesHolder(@NonNull View itemView,ImagesAdapter imagesAdapter){
            super(itemView);
            this.imagesList=itemView.findViewById(R.id.imageList);
            this.namesList=itemView.findViewById(R.id.nameList);
            this.imagesAdapter=imagesAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AdapterData positionData=dataSupplier.getData().get(getLayoutPosition());
            Intent intent = new Intent(context, DescriptionActivity.class);
            intent.putExtra("name", positionData.name);
            intent.putExtra("description", positionData.description);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            positionData.bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte[] bytes = stream.toByteArray();
            intent.putExtra("image",bytes);
            context.startActivity(intent);
        }
    }


    @NonNull
    @Override
    public ImagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View position=layoutInflater.inflate(R.layout.position_recycle_view,parent,false);
        return new ImagesHolder(position,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesHolder holder, int position) {
        AdapterData adapterData = dataSupplier.getData().get(position);
        holder.namesList.setText(adapterData.name);
        holder.imagesList.setImageBitmap(adapterData.bitmap);
    }

    @Override
    public int getItemCount() {
        return dataSupplier.getData().size();
    }
}
