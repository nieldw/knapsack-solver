package com.github.nieldw.knapsack.matcher

import com.github.nieldw.knapsack.Item
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ItemMatcher(private val expectedItem: Item) : TypeSafeMatcher<Item>() {
    override fun matchesSafely(item: Item): Boolean {
        return item.index == expectedItem.index &&
                item.weight.compareTo(expectedItem.weight) == 0 &&
                item.value.compareTo(expectedItem.value) == 0
    }

    override fun describeTo(description: Description) {
        description.appendText("an Item with")
                .appendText("\nIndex: ").appendValue(expectedItem.index)
                .appendText("\nWeight: ").appendValue(expectedItem.weight)
                .appendText("\nValue: ").appendValue(expectedItem.value)
    }

    companion object {
        fun matching(expectedItem: Item) = ItemMatcher(expectedItem)
    }
}