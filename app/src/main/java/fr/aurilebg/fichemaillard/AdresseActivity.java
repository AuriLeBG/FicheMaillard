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

public class AdresseActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_adresse);

        Button buttonOk = findViewById(R.id.buttonOk);
        Button buttonCancel = findViewById(R.id.buttonCancel);

        Intent intent = getIntent();

        String numero = intent.getStringExtra("fr.aurilebg.fichemaillard.numero");
        String rue = intent.getStringExtra("fr.aurilebg.fichemaillard.rue");
        String code_postal = intent.getStringExtra("fr.aurilebg.fichemaillard.code_postal");
        String ville = intent.getStringExtra("fr.aurilebg.fichemaillard.ville");

        EditText editTextNumero = findViewById(R.id.editTextNumero);
        EditText editTextRue = findViewById(R.id.editTextRue);
        EditText editTextCodePostal = findViewById(R.id.editTextCodePostal);
        EditText editTextVille = findViewById(R.id.editTextVille);

        SetEditText(editTextNumero, numero);
        SetEditText(editTextRue, rue);
        SetEditText(editTextCodePostal, code_postal);
        SetEditText(editTextVille, ville);

        buttonOk.setOnClickListener(v->{
            Intent result = new Intent();

            result.putExtra("fr.aurilebg.fichemaillard.numero", editTextNumero.getText().toString());
            result.putExtra("fr.aurilebg.fichemaillard.rue", editTextRue.getText().toString());
            result.putExtra("fr.aurilebg.fichemaillard.code_postal", editTextCodePostal.getText().toString());
            result.putExtra("fr.aurilebg.fichemaillard.ville", editTextVille.getText().toString());
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