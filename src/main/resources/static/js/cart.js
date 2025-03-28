document.addEventListener("DOMContentLoaded", function () {
    loadCart();
});

function loadCart() {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    let cartItems = document.getElementById("cart-items");
    let cartTotal = document.getElementById("cart-total");
    let total = 0;

    cartItems.innerHTML = ""; 
    cart.forEach((item, index) => {
        let itemTotal = item.price * item.quantity;
        total += itemTotal;
        cartItems.innerHTML += `
            <tr>
                <td>${item.name}</td>
                <td>$${item.price}</td>
                <td><input type="number" value="${item.quantity}" min="1" onchange="updateQuantity(${index}, this.value)"></td>
                <td>$${itemTotal}</td>
                <td><button class="btn btn-danger btn-sm" onclick="removeFromCart(${index})">Remove</button></td>
            </tr>
        `;
    });

    cartTotal.innerText = total.toFixed(2);
}

function updateQuantity(index, quantity) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart[index].quantity = parseInt(quantity);
    localStorage.setItem("cart", JSON.stringify(cart));
    loadCart();
}

function removeFromCart(index) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart.splice(index, 1);
    localStorage.setItem("cart", JSON.stringify(cart));
    loadCart();
}

function checkout() {
    alert("Checkout successful! Order placed.");
    localStorage.removeItem("cart");
    loadCart();
}
