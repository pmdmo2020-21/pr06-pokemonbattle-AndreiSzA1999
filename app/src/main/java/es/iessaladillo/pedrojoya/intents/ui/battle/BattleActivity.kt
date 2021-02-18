package es.iessaladillo.pedrojoya.intents.ui.battle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.intents.data.local.Database.getPokemonById
import es.iessaladillo.pedrojoya.intents.data.local.Database.getRandomPokemon
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.BattleActivityBinding
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity
import es.iessaladillo.pedrojoya.intents.ui.winner.WinnerActivity
import java.lang.RuntimeException

private const val RC_SELECTION_ACTIVITY = 0

class BattleActivity : AppCompatActivity() {

    private lateinit var binding: BattleActivityBinding
    private lateinit var pokemon: Pokemon
    private lateinit var pokemon2:Pokemon


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BattleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialstate()
        setviews()
    }


    private fun initialstate() {
        pokemon = getRandomPokemon()
        pokemon2 = getRandomPokemon()
        binding.imgPokemon1.setImageDrawable(getDrawable(pokemon.imagen))
        binding.imgPokemon2.setImageDrawable(getDrawable(pokemon2.imagen))
        binding.txtFirstPokemon.text = pokemon.nombre
        binding.txtSecondName.text = pokemon2.nombre
    }
    private fun setviews() {
        binding.PrimerLayout.setOnClickListener {selectionActivityLayout(pokemon,1)}
        binding.SegundoLayout.setOnClickListener {selectionActivityLayout(pokemon2,2)}
        binding.btnStartBattle.setOnClickListener { winnerActivity() }
    }

    private fun winnerActivity() {
        val intentn = WinnerActivity.newIntent(this,pokemonId1 = pokemon.id,pokemonId2 = pokemon2.id)
        startActivity(intentn)
    }

    private fun makechanges() {
        binding.imgPokemon1.setImageDrawable(getDrawable(pokemon.imagen))
        binding.imgPokemon2.setImageDrawable(getDrawable(pokemon2.imagen))
        binding.txtFirstPokemon.text = pokemon.nombre
        binding.txtSecondName.text = pokemon2.nombre
    }



    private fun selectionActivityLayout(pokemon: Pokemon, screen: Int) {
        val intent = SelectionActivity.newIntent(this,pokemon.id,screen)
        startActivityForResult(intent,RC_SELECTION_ACTIVITY)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == RC_SELECTION_ACTIVITY && data != null){
            extractResult(data)
        }
    }

    private fun extractResult(intent: Intent) {
        if(!intent.hasExtra(SelectionActivity.ID) || !intent.hasExtra(SelectionActivity.SCREEN)){
            throw RuntimeException("Error receiving results")
        }
        if(intent.getIntExtra(SelectionActivity.SCREEN,0) == 1){
            pokemon = getPokemonById(intent.getLongExtra(SelectionActivity.ID,0))!!
        }else{
            pokemon2 = getPokemonById(intent.getLongExtra(SelectionActivity.ID,0))!!
        }

        makechanges()
    }
}