package fr.aurilebg.fichemaillard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IndentityActivity extends AppCompatActivity {

    // Permet de set les editText en prennant en compte les "Inconnu"
    private void SetEditText(EditText editText, String value)
    {
        if(value == null || value.equals(getString(R.string.inconnu)))
        {
            editText.setText("");
        }
        else {
            editText.setText(value);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_indentity);

        Button buttonOk = findViewById(R.id.buttonOk);
        Button buttonCancel = findViewById(R.id.buttonCancel);

        //Récupération de l'intention envoyé par l'activité principale
        Intent intent = getIntent();

        //Récupération des données envoyés par l'intention
        String nom = intent.getStringExtra("fr.aurilebg.fichemaillard.nom");
        String prenom = intent.getStringExtra("fr.aurilebg.fichemaillard.prenom");
        String telephone = intent.getStringExtra("fr.aurilebg.fichemaillard.telephone");

        //Récupere et set les EditText
        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextPrenom = findViewById(R.id.editTextPrenom);
        EditText editTextTelephone = findViewById(R.id.editTextTelephone);

        SetEditText(editTextNom, nom);
        SetEditText(editTextPrenom, prenom);
        SetEditText(editTextTelephone, telephone);

        buttonOk.setOnClickListener(v->{
            Intent result = new Intent(); //Création d'une intention de résultat

            //Ajout des données dans l'intention
            result.putExtra("fr.aurilebg.fichemaillard.nom", editTextNom.getText().toString());
            result.putExtra("fr.aurilebg.fichemaillard.prenom", editTextPrenom.getText().toString());
            result.putExtra("fr.aurilebg.fichemaillard.telephone", editTextTelephone.getText().toString());

            //Set le code d'erreur de l'intention pour dire que ça s'est bien passé
            setResult(RESULT_OK, result);

            //Envoi le résultat à l'activité principale
            finish();
        });

        buttonCancel.setOnClickListener( v-> {
            Intent result = new Intent();
            // Set le code d'erreur de l'intention pour dire qu'on annule
            setResult(RESULT_CANCELED, result);
            // Envoi le résultat à l'activité principale
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}