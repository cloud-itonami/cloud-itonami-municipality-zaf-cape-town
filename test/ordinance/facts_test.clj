(ns ordinance.facts-test
  (:require [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest cape-town-has-spec-basis
  (let [sb (facts/spec-basis "cape-town")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://") sb))
    (is (every? #(re-find #"capetown\.gov\.za" (:ordinance/url %)) sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "johannesburg")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["cape-town" "johannesburg"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["johannesburg"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["cape-town.city-ombudsman-bylaw-2025"]
         (mapv :ordinance/id (facts/by-topic "cape-town" :governance))))
  (is (empty? (facts/by-topic "cape-town" :labor)))
  (is (empty? (facts/by-topic "johannesburg" :urban-planning))))
