package pollub.ism.lab09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImagesAdapter.DataSupplier dataSupplier = new DataSupplierImpl(getResources());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ImagesAdapter adapter = new ImagesAdapter(this, dataSupplier);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}