package es.iessaladillo.pedrojoya.intents.ui.selection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.intents.data.local.Database.getPokemonById
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import es.iessaladillo.pedrojoya.intents.databinding.SelectionActivityBinding

class SelectionActivity : AppCompatActivity() {

    companion object{
        const val ID = "POKEMON_ID"
        const val SCREEN = "SCREEN"
        fun newIntent(context: Context, pokemonId: Long, typeScreen:Int) : Intent =
            Intent(context,SelectionActivity::class.java)
                .putExtras(bundleOf(ID to pokemonId, SCREEN to typeScreen))
    }


    private lateinit var binding : SelectionActivityBinding
    private lateinit var myPokemon:Pokemon
    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SelectionActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        initialStage()
        setupViews()
    }

    private fun setupViews() {
        listeners()
    }
    private fun initialStage() {
        checkPokemons(myPokemon.nombre)
    }
    private fun checkPokemons(nombre: String) {
        when(nombre){
            "Bulbasur" -> binding.rdoBulbasur.isChecked = true
            "Giratina" -> binding.rdoGiratina.isChecked = true
            "Cubone" -> binding.rdoCubone.isChecked = true
            "Gyarados" -> binding.rdoGyarados.isChecked = true
            "Feebas" -> binding.rdoFreebas.isChecked = true
            "Pikachu" -> binding.rdopikachu.isChecked = true
        }
    }
    private fun listeners() {
        binding.rdoBulbasur.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(1L)!!
        }
        binding.imgBulbasur.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(1L)!!
            binding.rdoBulbasur.isChecked = true
        }

        binding.rdoGiratina.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(2L)!!
        }
        binding.imggiratina.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(2L)!!
            binding.rdoGiratina.isChecked = true
        }

        binding.rdoCubone.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(3L)!!
        }
        binding.imgcubone.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(3L)!!
            binding.rdoCubone.isChecked = true
        }

        binding.rdoGyarados.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(4L)!!
        }
        binding.imggyarados.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(4L)!!
            binding.rdoGyarados.isChecked = true
        }

        binding.rdoFreebas.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(5L)!!
        }
        binding.imgfreebas.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(5L)!!
            binding.rdoFreebas.isChecked = true
        }

        binding.rdopikachu.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(6L)!!
        }
        binding.imgpikachu.setOnClickListener {unckeck(it)
            myPokemon = getPokemonById(6L)!!
            binding.rdopikachu.isChecked = true
        }
    }
    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(ID) || !intent.hasExtra(SCREEN)) {
            throw RuntimeException()
        }

        myPokemon = getPokemonById(intent.getLongExtra(ID,0))!!
        num = intent.getIntExtra(SCREEN,0)
    }
    private fun unckeck(view: View) {
        if(binding.rdoBulbasur != view){
            binding.rdoBulbasur.isChecked = false
        }
        if(binding.rdoCubone != view){
            binding.rdoCubone.isChecked = false
        }
        if(binding.rdoFreebas != view){
            binding.rdoFreebas.isChecked = false
        }
        if(binding.rdoGiratina != view){
            binding.rdoGiratina.isChecked = false
        }
        if(binding.rdopikachu != view){
            binding.rdopikachu.isChecked = false
        }
        if(binding.rdoGyarados != view){
            binding.rdoGyarados.isChecked = false
        }
    }
    override fun onBackPressed() {
        val result = Intent().putExtras(
           bundleOf( ID to myPokemon!!.id, SCREEN to num))
        setResult(RESULT_OK,result)
        super.onBackPressed()
    }



}