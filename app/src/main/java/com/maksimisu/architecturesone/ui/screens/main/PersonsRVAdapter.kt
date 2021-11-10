package com.maksimisu.architecturesone.ui.screens.main

import com.maksimisu.architecturesone.data.entities.Person
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maksimisu.architecturesone.databinding.ItemPersonBinding

class PersonsRVAdapter : RecyclerView.Adapter<PersonsRVAdapter.PersonsRVViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsRVViewHolder {
        return PersonsRVViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), mListener
        )
    }

    override fun onBindViewHolder(holder: PersonsRVViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.firstName == newItem.firstName && oldItem.lastName == newItem.lastName
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var persons: List<Person>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    inner class PersonsRVViewHolder(
        private val binding: ItemPersonBinding,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }

        fun bind() = with(binding) {
            val person = persons[adapterPosition]
            tvFirstname.text = person.firstName
            tvLastname.text = person.lastName
        }

    }

}