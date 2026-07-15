(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Cape Town -- the
  TWENTY-SECOND municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid, -kor-seoul, -ita-roma, -aus-sydney,
  -arg-buenos-aires, -fin-helsinki, -dnk-copenhagen, -nor-oslo,
  -bel-brussels, -chl-santiago, -col-bogota, -cri-san-jose,
  -bra-sao-paulo, -ury-montevideo for the first twenty-one) per
  ADR-2607141700 (cloud-itonami-compliance-fact-federation).

  Panama City (mupa.gob.pa) was attempted first this tick but blocked
  WebFetch entirely (HTTP 403 even at the bare domain root, matching
  the Lisbon/lisboa.pt whole-domain-block pattern seen in an earlier
  tick) -- abandoned without forcing it. Every entry here instead cites
  an OFFICIAL capetown.gov.za (City of Cape Town) URL -- never
  fabricated. An ordinance not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"cape-town"
   [{:ordinance/id "cape-town.municipal-planning-amendment-bylaw-2025"
     :ordinance/title "City of Cape Town Municipal Planning Amendment By-law, 2025"
     :ordinance/municipality "cape-town"
     :ordinance/country "ZAF"
     :ordinance/kind :ordinance
     :ordinance/url "https://resource.capetown.gov.za/documentcentre/Documents/Bylaws%20and%20policies/Municipal_Planning_Amendment_Bylaw_2025.pdf"
     :ordinance/url-provenance :official-capetown-gov-za
     :ordinance/enacted-date "2025-08-08"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:urban-planning}}
    {:ordinance/id "cape-town.city-ombudsman-bylaw-2025"
     :ordinance/title "City Ombudsman By-law, 2025"
     :ordinance/municipality "cape-town"
     :ordinance/country "ZAF"
     :ordinance/kind :ordinance
     :ordinance/url "https://www.capetown.gov.za/bylaws/"
     :ordinance/url-provenance :official-capetown-gov-za
     :ordinance/enacted-date "2025"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:governance}}]})

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
      :note (str "cloud-itonami-municipality-zaf-cape-town Wave 0 (ADR-2607141700): "
                 (count (get catalog "cape-town")) " Cape Town entries seeded "
                 "with an official capetown.gov.za citation. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
