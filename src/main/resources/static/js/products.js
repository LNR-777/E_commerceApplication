document.addEventListener("DOMContentLoaded", function () {
    fetchProducts();
});

function fetchProducts() {
    fetch("http://localhost:8080/api/products")
        .then(response => response.json())
        .then(products => {
            const productList = document.getElementById("product-list");
            productList.innerHTML = ""; 
            products.forEach(product => {
                productList.innerHTML += `
                    <div class="col-md-4">
                        <div class="card mb-3">
                            <div class="card-body">
                                <h5 class="card-title">${product.name}</h5>
                                <p class="card-text">${product.description}</p>
                                <p class="card-text"><strong>Price:</strong> $${product.price}</p>
                                <button class="btn btn-primary" onclick="addToCart(${product.id}, '${product.name}', ${product.price})">Add to Cart</button>
                            </div>
                        </div>
                    </div>
                `;
            });
        })
        .catch(error => console.error("Error fetching products:", error));
}

function addToCart(id, name, price) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart.push({ id, name, price, quantity: 1 });
    localStorage.setItem("cart", JSON.stringify(cart));
    alert("Product added to cart!");
}
