package hr.tvz.android.listamarinic

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class MainActivity : AppCompatActivity(), OnCryptoClickListener {

    private val cryptos = ArrayList<Crypto>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDummyCryptoList()

        var cryptoAdater = CryptoAdapter(cryptos, this)
        rv_crypto_list.layoutManager = LinearLayoutManager(this)
        rv_crypto_list.adapter = cryptoAdater
        cryptoAdater.notifyDataSetChanged()

    }

    fun createDummyCryptoList(){
        var titles = arrayOf("Bitcoin", "Ethereum", "Tether", "BNB", "Cardano")
        var prices = arrayOf(57000.00, 4300.00, 1.00, 670.00, 1.8)
        var supplies = arrayOf(21000000, 115840407, 57468026068, 170533651, 45000000000)
        var images = arrayOf(R.drawable.ic_launcher_cryptoappimage,R.drawable.ic_launcher_cryptoappimage,R.drawable.ic_launcher_cryptoappimage,R.drawable.ic_launcher_cryptoappimage,R.drawable.ic_launcher_cryptoappimage)


        for(i in 0..4){
            cryptos.add(Crypto(titles[i], prices[i], supplies[i]))
        }

    }

    override fun onCryptoItemClicked(position: Int) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("name", cryptos[position].name)
        intent.putExtra("price", cryptos[position].price)
        intent.putExtra("supply", cryptos[position].supply)
        //intent.putExtra("image", cryptos[position].image)
        startActivity(intent)

    }
}