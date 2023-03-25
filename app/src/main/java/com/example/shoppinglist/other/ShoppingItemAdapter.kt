package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter (
    var items : List<ShoppingItem>,
    private val viewModel : ShoppingViewModel
        ) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder> () {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {

            val itemBinding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ShoppingViewHolder(itemBinding)

//                val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
//                return ShoppingViewHolder(view)
        }

        override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
            val curShoppingItem  = items[position]
            with(holder){
                with(curShoppingItem){

                    binding.tvName.text = curShoppingItem.name
                    binding.tvAmount.text = "${curShoppingItem.amount}"

                    binding.ivDelete.setOnClickListener{
                        viewModel.delete(curShoppingItem)
                    }

                    binding.ivPlus.setOnClickListener {
                        curShoppingItem.amount++
                        viewModel.upsert(curShoppingItem)
                    }

                    binding.ivMinus.setOnClickListener {
                        if (curShoppingItem.amount > 0) {

                            curShoppingItem.amount--
                            viewModel.upsert(curShoppingItem)
                        }
                    }
                }
            }
        }

        override fun getItemCount(): Int {
                return items.size
        }

    inner class ShoppingViewHolder(val binding: ShoppingItemBinding) :RecyclerView.ViewHolder(binding.root)

}