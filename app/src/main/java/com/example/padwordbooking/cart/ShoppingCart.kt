package com.example.padwordbooking.cart

import com.example.padwordbooking.model.Customer
import com.example.padwordbooking.model.Product
import io.paperdb.Paper

class ShoppingCart {

    companion object {

        fun addItem(productItem: Product) {
            val products = getProducts()
            products.add(productItem)
            saveReservation(products)
        }

        fun removeItem(productItem: Product) {

            val products = getProducts()

            val targetItem = products.singleOrNull { it.availabilities[0].id == productItem.availabilities[0].id }
            if (targetItem != null) {
                products.remove(targetItem)
            }
            saveReservation(products)
        }

        fun saveReservation(cart: MutableList<Product>) {
            Paper.book().write("product", cart)
        }

        fun saveCustomer(customer: Customer) {
            Paper.book().write("customer", customer)
        }

        fun getProducts(): MutableList<Product> {
            return Paper.book().read("product", mutableListOf())
        }

        fun getCustomer(): Customer {
            return Paper.book().read("customer")
        }

    }

}