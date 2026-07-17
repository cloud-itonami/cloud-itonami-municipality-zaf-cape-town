(ns culture.facts
  "Regional-culture catalog for Cape Town -- local dishes, protected
  products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"cape-town"
   [{:culture/id "cape-town.dish.bobotie"
     :culture/name "Bobotie"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :dish
     :culture/summary "South African dish of spiced minced meat baked with an egg-based topping, adopted by the Cape Malay community and a cornerstone of South African cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Bobotie"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cape-town.dish.gatsby"
     :culture/name "Gatsby"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :dish
     :culture/summary "South African submarine sandwich that originated in Cape Town and is popular throughout the Western Cape province."
     :culture/url "https://en.wikipedia.org/wiki/Gatsby_(sandwich)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cape-town.dish.tomato-bredie"
     :culture/name "Tomato bredie"
     :culture/name-local "Tamatiebredie"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :dish
     :culture/summary "South African mutton stew spiced with cinnamon, cardamom, ginger and cloves, originating from the Cape region where it was introduced by Malays, now eaten throughout South Africa."
     :culture/url "https://en.wikipedia.org/wiki/Tomato_bredie"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cape-town.dish.malva-pudding"
     :culture/name "Malva pudding"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :dish
     :culture/summary "South African sweet pudding made with apricot jam, thought to be of Dutch or Cape Dutch origin, likely brought to the region by Dutch colonists in the mid-1600s."
     :culture/url "https://en.wikipedia.org/wiki/Malva_pudding"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cape-town.product.biltong"
     :culture/name "Biltong"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :product
     :culture/summary "Air-dried, cured meat that originated in Southern Africa, a traditional South African food that has become a cultural export."
     :culture/url "https://en.wikipedia.org/wiki/Biltong"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cape-town.beverage.rooibos"
     :culture/name "Rooibos"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :beverage
     :culture/summary "Caffeine-free herbal tea made from Aspalathus linearis, usually grown in the Cederberg in the Western Cape province of South Africa."
     :culture/url "https://en.wikipedia.org/wiki/Rooibos"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cape-town.festival.kaapse-klopse"
     :culture/name "Cape Town Minstrel Carnival"
     :culture/name-local "Kaapse Klopse"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :festival
     :culture/summary "Traditionally Cape Coloured minstrel festival held annually on 2 January in Cape Town, officially known as the Cape Town Minstrel Carnival."
     :culture/url "https://en.wikipedia.org/wiki/Kaapse_Klopse"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cape-town.heritage.table-mountain"
     :culture/name "Table Mountain"
     :culture/name-local "Tafelberg"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :heritage
     :culture/summary "Flat-topped mountain forming a prominent landmark overlooking the city of Cape Town."
     :culture/url "https://en.wikipedia.org/wiki/Table_Mountain"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cape-town.heritage.robben-island"
     :culture/name "Robben Island"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :heritage
     :culture/summary "Island in Table Bay north of Cape Town, designated a UNESCO World Heritage Site in 1999 for its cultural significance to South Africa's political history."
     :culture/url "https://en.wikipedia.org/wiki/Robben_Island"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "cape-town.heritage.bo-kaap"
     :culture/name "Bo-Kaap"
     :culture/municipality "cape-town"
     :culture/country "ZAF"
     :culture/kind :heritage
     :culture/summary "Historic residential area in Cape Town known for its brightly coloured homes and cobblestoned streets, a traditional center of Cape Malay culture and heritage."
     :culture/url "https://en.wikipedia.org/wiki/Bo-Kaap"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-zaf-cape-town culture catalog "
                 "(ADR-2607171400): " (count (get catalog "cape-town"))
                 " Cape Town entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
