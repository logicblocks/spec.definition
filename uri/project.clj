(defproject io.logicblocks/spec.definition.uri "0.0.1-RC3"
  :description "clojure.spec definitions for URI data types."

  :parent-project {:path    "../parent/project.clj"
                   :inherit [:scm
                             :url
                             :license
                             :plugins
                             [:profiles :parent-shared]
                             [:profiles :parent-dev]
                             [:profiles :parent-dev-specific]
                             [:profiles :parent-reveal]
                             [:profiles :parent-reveal-specific]
                             [:profiles :parent-flow-storm]
                             [:profiles :parent-flow-storm-specific]
                             [:profiles :parent-test]
                             [:profiles :parent-test-specific]
                             :deploy-repositories
                             :managed-dependencies
                             :cloverage
                             :bikeshed
                             :cljfmt
                             :eastwood]}

  :plugins [[lein-parent "0.3.8"]]

  :dependencies [[io.logicblocks/spec.definition.core]
                 [io.logicblocks/datatype.uri]]

  :profiles
  {:shared     ^{:pom-scope :test}
               {:dependencies [[lambdaisland/uri]
                               [io.logicblocks/datatype.testing]
                               [io.logicblocks/icu4clj]
                               [io.logicblocks/spec.validate]]}
   :reveal     [:parent-reveal :shared]
   :flow-storm [:parent-flow-storm :shared]
   :dev        [:parent-dev :shared]
   :test       [:parent-test :shared]})
