package hr.tvz.android.listamarinic

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_layout.view.*

class CryptoAdapter(private val cryptos : ArrayList<Crypto>, private val onCryptoClickListener: OnCryptoClickListener) : RecyclerView.Adapter<CryptoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent,false))
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val crypto = cryptos[position]

        holder.itemView.item_title.text = crypto.name

        holder.itemView.setOnClickListener{
            onCryptoClickListener.onCryptoItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return cryptos.size
    }

}