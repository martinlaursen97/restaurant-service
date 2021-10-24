const products = [1,2,3];
function addValue(value) {
    products.push(value);
    return alert(value);
}
function displayValues() {
    return products.join(",");
}
function getProducts() {
    return products;
}

