INSERT INTO category (id, name) VALUES
(1, 'Protein & Energy Bars'),
(2, 'Nuts & Trail Mix'),
(3, 'Cold Beverages'),
(4, 'Low Sugar Snacks')
ON DUPLICATE KEY UPDATE name=VALUES(name);

INSERT INTO product (product_id, name, price, weight, img_name, id) VALUES
(1, 'Choco Protein Bar', 60.0, 50.0, 'protein_bar.jpg', 1),
(2, 'Almond Trail Mix', 120.0, 200.0, 'trail_mix.jpg', 2),
(3, 'Cold Brew Coffee', 90.0, 250.0, 'cold_brew.jpg', 3),
(4, 'Sugar-Free Granola Bites', 80.0, 150.0, 'granola_bites.jpg', 4),
(5, 'Peanut Butter Cups', 110.0, 180.0, 'peanut_cups.jpg', 4),
(6, 'Coconut Water', 70.0, 300.0, 'coconut_water.jpg', 3)
ON DUPLICATE KEY UPDATE name=VALUES(name), price=VALUES(price), weight=VALUES(weight), img_name=VALUES(img_name), id=VALUES(id);
