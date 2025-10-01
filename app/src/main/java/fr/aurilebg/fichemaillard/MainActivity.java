package fr.aurilebg.fichemaillard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> mGetResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        String name = data.getStringExtra("fr.aurilebg.fichemaillard.nom");
                        String prenom = data.getStringExtra("fr.aurilebg.fichemaillard.prenom");
                        String telephone = data.getStringExtra("fr.aurilebg.fichemaillard.telephone");
                        Toast.makeText(MainActivity.this,
                                "Nom reçu : " + name +
                                "prenom reçu : " + prenom +
                                "telephone reçu : " + telephone,
                                Toast.LENGTH_SHORT).show();

                        TextView textViewNom = findViewById(R.id.textViewNomUser);
                        TextView textViewPrenom = findViewById(R.id.textViewPrenomUser);
                        TextView textViewTelephone = findViewById(R.id.textViewTelephoneUser);

                        textViewNom.setText(name);
                        textViewPrenom.setText(prenom);
                        textViewTelephone.setText(telephone);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.buttonIdentity);

        button.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, IndentityActivity.class);

            TextView textViewNom = findViewById(R.id.textViewNomUser);
            TextView textViewPrenom = findViewById(R.id.textViewPrenomUser);
            TextView textViewTelephone = findViewById(R.id.textViewTelephoneUser);

            String nom = textViewNom.getText().toString();
            String prenom = textViewPrenom.getText().toString();
            String telephone = textViewTelephone.getText().toString();

            intent.putExtra("fr.aurilebg.fichemaillard.nom", nom);
            intent.putExtra("fr.aurilebg.fichemaillard.prenom", prenom);
            intent.putExtra("fr.aurilebg.fichemaillard.telephone", telephone);

            mGetResult.launch(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}