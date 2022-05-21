package mx.edu.ittepic.adacova.u3practica1basedatossqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.databinding.ActivityMainBinding
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.fragments.HomeFragment
import mx.edu.ittepic.adacova.u3practica1basedatossqlite.fragments.Option2Fragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val option2Fragment = Option2Fragment()

        miFragmento(homeFragment)

        val bottom_navigation = binding.bottomNav
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> miFragmento(homeFragment)
                R.id.option2 -> miFragmento(option2Fragment)
            }
            true
        }
    }
        fun miFragmento(fragmento: Fragment){
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.frame_nav,fragmento)
                commit()
            }
        }
    }
