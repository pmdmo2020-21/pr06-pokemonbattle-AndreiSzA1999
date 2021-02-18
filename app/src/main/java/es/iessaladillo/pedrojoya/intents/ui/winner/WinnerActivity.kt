package es.iessaladillo.pedrojoya.intents.ui.winner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.intents.data.local.Database
import es.iessaladillo.pedrojoya.intents.data.local.Database.getPokemonById
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.WinnerActivityBinding
import es.iessaladillo.pedrojoya.intents.ui.selection.SelectionActivity

class WinnerActivity : AppCompatActivity() {



    companion object{
        const val IDPOKEMON1 = "IDPOKEMON1"
        const val IDPOKEMON2 = "IDPOKEMON2"
        fun newIntent(context: Context, pokemonId1: Long, pokemonId2: Long) : Intent =
            Intent(context, WinnerActivity::class.java)
                .putExtras(bundleOf(IDPOKEMON1 to pokemonId1, IDPOKEMON2 to pokemonId2))
    }



    private lateinit var binding: WinnerActivityBinding
    private lateinit var pokemon1: Pokemon
    private lateinit var pokemon2: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WinnerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
    }

    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(IDPOKEMON1) || !intent.hasExtra(
                IDPOKEMON2
            )) {
            throw RuntimeException()
        }
        pokemon1 = getPokemonById(intent.getLongExtra(IDPOKEMON1,0))!!
        pokemon2 = getPokemonById(intent.getLongExtra(IDPOKEMON2,0))!!

        determineWinner()
    }

    private fun determineWinner() {
        if(pokemon1.fuerza > pokemon2.fuerza){
            binding.winnerImage.setImageResource(pokemon1.imagen)
            binding.NamePokemonWiinner.text = pokemon1.nombre
        }else if(pokemon1.fuerza < pokemon2.fuerza){
            binding.winnerImage.setImageResource(pokemon2.imagen)
            binding.NamePokemonWiinner.text = pokemon2.nombre
        }else{
            binding.winnerImage.setImageResource(pokemon2.imagen)
            binding.NamePokemonWiinner.text = "EMPATE"
        }
    }

}