package es.iessaladillo.pedrojoya.intents.data.local

import es.iessaladillo.pedrojoya.intents.R
import es.iessaladillo.pedrojoya.intents.data.local.model.Pokemon
import java.util.concurrent.ThreadLocalRandom

// TODO: Haz que Database implemente DataSource
object Database : DataSource {
    override fun getRandomPokemon(): Pokemon {
        var pokemon : Pokemon? = null
        var lista = getAllPokemons()
        pokemon = lista[ThreadLocalRandom.current().nextInt(0,6)]
        return pokemon
    }
    override fun getAllPokemons(): List<Pokemon> {
        var pokemons = listOf<Pokemon>(
            Pokemon(1, "Bulbasur", 1, R.drawable.bulbasur),
            Pokemon(2, "Giratina", 2, R.drawable.giratina),
            Pokemon(3, "Cubone", 3, R.drawable.cubone),
            Pokemon(4, "Gyarados", 4, R.drawable.gyarados),
            Pokemon(5, "Feebas", 5, R.drawable.feebas),
            Pokemon(6, "Pikachu", 6, R.drawable.pikachu),
            )
        return pokemons
    }
    override fun getPokemonById(id: Long): Pokemon? {
        var lista = getAllPokemons()
        var pokemon : Pokemon? = null

        lista.forEach{
            if (it.id == id){
                pokemon = it
            }
        }
        return pokemon
        }
    }


