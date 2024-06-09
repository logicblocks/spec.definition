(defproject io.logicblocks/spec.definition.parent "0.0.1-RC3"
  :scm {:dir  "."
        :name "git"
        :url  "https://github.com/logicblocks/spec.definition"}

  :url "https://github.com/logicblocks/spec.definition"

  :license
  {:name "The MIT License"
   :url  "https://opensource.org/licenses/MIT"}

  :plugins [[io.logicblocks/lein-interpolate "0.1.1-RC3"]
            [jonase/eastwood "1.4.2"]
            [lein-ancient "0.7.0"]
            [lein-bikeshed "0.5.2"]
            [lein-cljfmt "0.9.2"]
            [lein-cloverage "1.2.4"]
            [lein-cprint "1.3.3"]
            [lein-eftest "0.6.0"]
            [lein-kibit "0.1.8"]
            [lein-shell "0.5.0"]
            [fipp "0.6.26"]]

  :deploy-repositories
  {"releases"  {:url "https://repo.clojars.org" :creds :gpg}
   "snapshots" {:url "https://repo.clojars.org" :creds :gpg}}

  :managed-dependencies
  [[org.clojure/clojure "1.11.3"]
   [org.clojure/test.check "1.1.1"]

   [io.logicblocks/icu4clj "0.0.1-RC3"]

   [io.logicblocks/datatype.core "0.0.1-RC10"]
   [io.logicblocks/datatype.testing "0.0.1-RC10"]
   [io.logicblocks/datatype.address "0.0.1-RC10"]
   [io.logicblocks/datatype.bool "0.0.1-RC10"]
   [io.logicblocks/datatype.collection "0.0.1-RC10"]
   [io.logicblocks/datatype.currency "0.0.1-RC10"]
   [io.logicblocks/datatype.domain "0.0.1-RC10"]
   [io.logicblocks/datatype.email "0.0.1-RC10"]
   [io.logicblocks/datatype.network "0.0.1-RC10"]
   [io.logicblocks/datatype.number "0.0.1-RC10"]
   [io.logicblocks/datatype.phone "0.0.1-RC10"]
   [io.logicblocks/datatype.string "0.0.1-RC10"]
   [io.logicblocks/datatype.time "0.0.1-RC10"]
   [io.logicblocks/datatype.uri "0.0.1-RC10"]
   [io.logicblocks/datatype.uuid "0.0.1-RC10"]

   [io.logicblocks/spec.validate "0.2.0-RC21"]

   [io.logicblocks/spec.definition.core :project/version]
   [io.logicblocks/spec.definition.address :project/version]
   [io.logicblocks/spec.definition.bool :project/version]
   [io.logicblocks/spec.definition.collection :project/version]
   [io.logicblocks/spec.definition.currency :project/version]
   [io.logicblocks/spec.definition.email :project/version]
   [io.logicblocks/spec.definition.number :project/version]
   [io.logicblocks/spec.definition.phone :project/version]
   [io.logicblocks/spec.definition.string :project/version]
   [io.logicblocks/spec.definition.time :project/version]
   [io.logicblocks/spec.definition.uri :project/version]
   [io.logicblocks/spec.definition.uuid :project/version]

   [lambdaisland/uri "1.19.155"]

   [com.widdindustries/cljc.java-time "0.1.21"]

   [nrepl "1.1.2"]

   [eftest "0.6.0"]

   [vlaaad/reveal "1.3.282"]

   [com.github.flow-storm/clojure "1.11.3-1"]
   [com.github.flow-storm/flow-storm-dbg "3.15.5"]]

  :profiles
  {:parent-shared
   ^{:pom-scope :test}
   {:dependencies
    [[org.clojure/clojure]
     [org.clojure/test.check]

     [com.github.flow-storm/clojure]
     [com.github.flow-storm/flow-storm-dbg]

     [vlaaad/reveal]

     [nrepl]

     [eftest]]}

   :parent-dev-specific
   ^{:pom-scope :test}
   {:source-paths ["dev"]
    :eftest       {:multithread? false}}

   :parent-flow-storm-specific
   ^{:pom-scope :test}
   {:exclusions [org.clojure/clojure]
    :jvm-opts   ["-Dclojure.storm.instrumentEnable=true"
                 "-Dclojure.storm.instrumentOnlyPrefixes=spec.validate,clojure.spec"]}

   :parent-reveal-specific
   ^{:pom-scope :test}
   {:repl-options {:nrepl-middleware [vlaaad.reveal.nrepl/middleware]}
    :jvm-opts     ["-Dvlaaad.reveal.prefs={:theme :light}"]}

   :parent-test-specific
   ^{:pom-scope :test}
   {:eftest {:multithread? false}}

   :parent-dev
   ^{:pom-scope :test}
   [:parent-shared :parent-dev-specific]

   :parent-flow-storm
   ^{:pom-scope :test}
   [:parent-shared :parent-flow-storm-specific]

   :parent-reveal
   ^{:pom-scope :test}
   [:parent-shared :parent-reveal-specific]

   :parent-test
   ^{:pom-scope :test}
   [:parent-shared :parent-test-specific]}

  :source-paths []
  :test-paths []
  :resource-paths []

  :cloverage
  {:ns-exclude-regex [#"^user"]}

  :bikeshed
  {:name-collisions false
   :long-lines      false}

  :cljfmt
  {:indents {#".*"     [[:inner 0]]
             defrecord [[:block 1] [:inner 1]]
             deftype   [[:block 1] [:inner 1]]}}

  :eastwood
  {:config-files
   [~(str (System/getProperty "user.dir") "/config/linter.clj")]})
