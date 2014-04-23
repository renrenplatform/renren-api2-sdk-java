/**
 * @(#)TestObjectMapper.java, 2012-12-5. 
 * 
 * Copyright 2012 RenRen, Inc. All rights reserved.
 */
package com.renren.api.mapper;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.codehaus.jackson.map.DeserializationConfig;
import org.junit.Test;

/**
 * @author Xun Dai <xun.dai@renren-inc.com>
 * 
 */
public class TestObjectMapper {

    public static class Product {

        private String name;

        private int amount;

        private double price;

        private Product innerProduct;

        private List<Product> productList;

        private Product[] productArray;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Product getInnerProduct() {
            return innerProduct;
        }

        public void setInnerProduct(Product innerProduct) {
            this.innerProduct = innerProduct;
        }

        public List<Product> getProductList() {
            return productList;
        }

        public void setProductList(List<Product> productList) {
            this.productList = productList;
        }

        public Product[] getProductArray() {
            return productArray;
        }

        public void setProductArray(Product[] productArray) {
            this.productArray = productArray;
        }

        @Override
        public String toString() {
            return "Product [name=" + name + ", amount=" + amount + ", price=" + price
                    + ", innerProduct=" + innerProduct + ", productList="
                    + (productList == null ? "null" : Arrays.toString(productList.toArray()))
                    + ", productArray=" + Arrays.toString(productArray) + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + amount;
            result = prime * result + ((innerProduct == null) ? 0 : innerProduct.hashCode());
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            long temp;
            temp = Double.doubleToLongBits(price);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            result = prime * result + Arrays.hashCode(productArray);
            result = prime * result + ((productList == null) ? 0 : productList.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Product other = (Product) obj;
            if (amount != other.amount) return false;
            if (innerProduct == null) {
                if (other.innerProduct != null) return false;
            } else if (!innerProduct.equals(other.innerProduct)) return false;
            if (name == null) {
                if (other.name != null) return false;
            } else if (!name.equals(other.name)) return false;
            if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) return false;
            if (!Arrays.equals(productArray, other.productArray)) return false;
            if (productList == null) {
                if (other.productList != null) return false;
            } else if (!productList.equals(other.productList)) return false;
            return true;
        }

    }

    @Test
    public void testJSONMaper() throws Throwable {
        try {
            String jsonString = "{\"name\":\"potato\",\"amount\":10,\"price\":2.5,\"innerProduct\":{\"name\":\"potato\",\"amount\":10,\"price\":2.5,\"innerProduct\":null,\"productList\":null,\"productArray\":null},\"productList\":[{\"name\":\"potato\",\"amount\":10,\"price\":2.5,\"innerProduct\":{\"name\":\"potato\",\"amount\":10,\"price\":2.5,\"innerProduct\":null,\"productList\":null,\"productArray\":null},\"productList\":null,\"productArray\":null},{\"name\":\"potato\",\"amount\":10,\"price\":2.5,\"innerProduct\":{\"name\":\"potato\",\"amount\":10,\"price\":2.5,\"innerProduct\":null,\"productList\":null,\"productArray\":null},\"productList\":null,\"productArray\":null},{\"name\":\"potato\",\"amount\":10,\"price\":2.5,\"innerProduct\":{\"name\":\"potato\",\"amount\":10,\"price\":2.5,\"innerProduct\":null,\"productList\":null,\"productArray\":null},\"productList\":null,\"productArray\":null}],\"productArray\":null}";
            org.codehaus.jackson.map.ObjectMapper jom = new org.codehaus.jackson.map.ObjectMapper();
            ObjectMapper om = new ObjectMapper();

            Product omPro = om.map(jsonString, Product.class);
            Product jomPro = jom.readValue(jsonString, Product.class);
            Assert.assertEquals(omPro, jomPro);
            

            // disable掉FAIL_ON_UNKNOWN_PROPERTIES属性，增强容错性
            jom.getDeserializationConfig().without(
                    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

            String json = jom.writeValueAsString(omPro);
            System.out.println(json);
            Product parsedProduct = (Product) om.mapCommon(json, Product.class);
            System.out.println(parsedProduct);
            Assert.assertEquals(omPro, parsedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        // mapper.getDeserializationConfig().setAnnotationIntrospector(new ThriftAnnotationIntrospector());

    }
}
