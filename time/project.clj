(defproject io.logicblocks/spec.definition.time "0.0.1-RC2"
  :description "clojure.spec definitions for time data types."

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
                 [io.logicblocks/datatype.time]]

  :profiles
  {:shared     ^{:pom-scope :test}
               {:dependencies [[com.widdindustries/cljc.java-time]
                               [io.logicblocks/datatype.testing]
                               [io.logicblocks/spec.validate]]}
   :reveal     [:parent-reveal :shared]
   :flow-storm [:parent-flow-storm :shared]
   :dev        [:parent-dev :shared]
   :test       [:parent-test :shared]})
