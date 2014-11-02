(ns simple-ini.core-test
  (:require [clojure.test :refer :all]
            [simple-ini.core :refer :all]))


(def test-doc
  {:database { :server "example.com"
               :port 5432
               :file "payroll.dat"}
   :name "John Doe"
   :organisation "Acme Widgets Inc." })

(def test-ini
  "[:database]\n:server = example.com\n:port = 5432\n:file = payroll.dat\n\n")

(deftest a-test
  (testing "Test that formatted config is same as test string"
    (is (= test-ini (serialize test-doc)))))
