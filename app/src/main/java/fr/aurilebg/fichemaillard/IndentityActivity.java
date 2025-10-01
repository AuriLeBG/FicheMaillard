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

        Intent intent = getIntent();

        String nom = intent.getStringExtra("fr.aurilebg.fichemaillard.nom");
        String prenom = intent.getStringExtra("fr.aurilebg.fichemaillard.prenom");
        String telephone = intent.getStringExtra("fr.aurilebg.fichemaillard.telephone");

        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextPrenom = findViewById(R.id.editTextPrenom);
        EditText editTextTelephone = findViewById(R.id.editTextTelephone);

        SetEditText(editTextNom, nom);
        SetEditText(editTextPrenom, prenom);
        SetEditText(editTextTelephone, telephone);
        
        buttonOk.setOnClickListener(v->{
            Intent result = new Intent();

            result.putExtra("fr.aurilebg.fichemaillard.nom", editTextNom.getText().toString());
            result.putExtra("fr.aurilebg.fichemaillard.prenom", editTextPrenom.getText().toString());
            result.putExtra("fr.aurilebg.fichemaillard.telephone", editTextTelephone.getText().toString());
            setResult(RESULT_OK, result);
            finish();
        });

        buttonCancel.setOnClickListener( v-> {
            Intent result = new Intent();
            setResult(RESULT_CANCELED, result);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}