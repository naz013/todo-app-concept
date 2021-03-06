package com.github.naz013.todoappconcept.home.view

import com.github.naz013.todoappconcept.arch.BaseView
import com.github.naz013.todoappconcept.data.DateRange
import com.github.naz013.todoappconcept.data.ListEvent
import com.github.naz013.todoappconcept.views.DateSelectorView
import java.util.*

interface HomeView : BaseView {
    fun showDates(dates: List<DateSelectorView.DateItem<DateRange>>)
    fun showEvents(events: List<ListEvent>)
    fun openAddDialog(date: Date)
}