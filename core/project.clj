(defproject io.logicblocks/spec.definition.core "0.0.1-RC0"
  :description
  "A support library for clojure.spec definitions for common data types."

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

  :dependencies [[io.logicblocks/spec.validate]]

  :profiles
  {:reveal     [:parent-reveal]
   :flow-storm [:parent-flow-storm]
   :dev        [:parent-dev]
   :test       [:parent-test]})
