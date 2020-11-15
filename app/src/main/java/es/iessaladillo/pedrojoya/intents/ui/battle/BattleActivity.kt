package es.iessaladillo.pedrojoya.intents.ui.battle

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding

class BattleActivity : AppCompatActivity() {

    private lateinit var binding: BattleActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BattleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialstate()
        setviews()
    }

    private fun initialstate() {

    }

    private fun setviews() {

        binding.PrimerLayout.setOnClickListener {}
        binding.SegundoLayout.setOnClickListener {}
        binding.btnStartBattle.setOnClickListener { }
    }



}