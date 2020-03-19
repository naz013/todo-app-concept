package com.github.naz013.todoappconcept.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.naz013.todoappconcept.arch.BaseFragment
import com.github.naz013.todoappconcept.data.DateRange
import com.github.naz013.todoappconcept.data.FolderWithEvents
import com.github.naz013.todoappconcept.databinding.FragmentHomeBinding
import com.github.naz013.todoappconcept.home.add.AddTaskDialog
import com.github.naz013.todoappconcept.home.presenter.HomePresenter
import com.github.naz013.todoappconcept.home.view.HomeView
import com.github.naz013.todoappconcept.views.DateSelectorView
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeView, HomePresenter>(), HomeView {

    private val addTaskDialog = AddTaskDialog().apply {
        saveCallback = {
            this@HomeFragment.presenter.reloadEvents()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener { presenter.addButtonClick() }
        binding.dateSelectorView.onDateSelectedListener =
            object : DateSelectorView.OnDateSelectedListener {
                override fun onDateSelected(position: Int, dateItem: DateSelectorView.DateItem<*>) {
                    onDateClicked(position, dateItem.payload)
                }
            }
        presenter.loadDates()
    }

    private fun onDateClicked(position: Int, payload: Any?) {
        if (payload != null && payload is DateRange) {
            presenter.loadEvents(payload)
        }
    }

    override fun view(): HomeView = this

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun showDates(dates: List<DateSelectorView.DateItem<DateRange>>) {
        binding.dateSelectorView.showDates(dates)
    }

    override fun showEvents(events: List<FolderWithEvents>) {
        binding.dateSelectorView.updateCounter(events.sumBy { it.events.size })
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun openAddDialog(date: Date) {
        addTaskDialog.showDialog(context!!, date)
    }
}