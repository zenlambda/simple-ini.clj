# clj-config-ini

A library for writing ini files.

## Usage

```clojure
(ns myproject.core
  (:require clj-config-ini.core :as ini))

(def test-doc
  {:database { :server "example.com"
               :port 5432
               :file "payroll.dat"}
   :name "John Doe"
   :organisation "Acme Widgets Inc." })

(def write-ini [file]
  (spit file (ini/format-config test-doc)))
```

## License

Copyright © 2014 Frank A. J. Wilson

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
