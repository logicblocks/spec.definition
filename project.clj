(defproject io.logicblocks/spec.definition "0.0.1-RC3"
  :description "Aggregate project for all spec.definition modules."

  :parent-project {:path    "parent/project.clj"
                   :inherit [:scm
                             :url
                             :license
                             :plugins
                             [:profiles :parent-shared]
                             :deploy-repositories
                             :managed-dependencies]}

  :plugins [[io.logicblocks/lein-interpolate "0.1.1-RC3"]
            [lein-parent "0.3.9"]
            [lein-sub "0.3.0"]
            [lein-changelog "0.3.2"]
            [lein-codox "0.10.8"]]

  :sub ["parent"
        "core"
        "string"
        "address"
        "bool"
        "collection"
        "currency"
        "email"
        "number"
        "phone"
        "time"
        "uri"
        "uuid"
        "."]

  :dependencies [[io.logicblocks/spec.definition.core]
                 [io.logicblocks/spec.definition.string]
                 [io.logicblocks/spec.definition.address]
                 [io.logicblocks/spec.definition.bool]
                 [io.logicblocks/spec.definition.collection]
                 [io.logicblocks/spec.definition.currency]
                 [io.logicblocks/spec.definition.email]
                 [io.logicblocks/spec.definition.number]
                 [io.logicblocks/spec.definition.phone]
                 [io.logicblocks/spec.definition.time]
                 [io.logicblocks/spec.definition.uri]
                 [io.logicblocks/spec.definition.uuid]]

  :profiles
  {:codox-specific
   {:dependencies [[io.logicblocks/spec.definition.core :project/version]
                   [org.clojure/data.csv]
                   [com.googlecode.libphonenumber/libphonenumber]
                   [lambdaisland/uri]
                   [com.widdindustries/cljc.java-time]]

    :source-paths ["core/src"
                   "string/src"
                   "address/src"
                   "bool/src"
                   "collection/src"
                   "currency/src"
                   "email/src"
                   "number/src"
                   "phone/src"
                   "time/src"
                   "uri/src"
                   "uuid/src"]}

   :test
   {:aliases {"eftest"
              ["sub"
               "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid"
               "with-profile" "test"
               "eftest"]}}

   :codox
   [:parent-shared :codox-specific]

   :prerelease
   {:release-tasks
    [
     ["vcs" "assert-committed"]
     ["sub" "change" "version" "leiningen.release/bump-version" "rc"]
     ["sub" "change" "version" "leiningen.release/bump-version" "release"]
     ["vcs" "commit" "Pre-release version %s [skip ci]"]
     ["vcs" "tag"]
     ["sub" "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid:." "deploy"]]}

   :release
   {:release-tasks
    [["vcs" "assert-committed"]
     ["sub" "change" "version" "leiningen.release/bump-version" "release"]
     ["sub" "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid:." "install"]
     ["changelog" "release"]
     ["shell" "sed" "-E" "-i.bak" "s/spec\\.definition\\.(.+) \"[0-9]+\\.[0-9]+\\.[0-9]+\"/spec\\.definition\\.\\\\1 \"${:version}\"/g" "README.md"]
     ["shell" "rm" "-f" "README.md.bak"]
     ["codox"]
     ["shell" "git" "add" "."]
     ["vcs" "commit" "Release version %s [skip ci]"]
     ["vcs" "tag"]
     ["sub" "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid:." "deploy"]
     ["sub" "change" "version" "leiningen.release/bump-version" "patch"]
     ["sub" "change" "version" "leiningen.release/bump-version" "rc"]
     ["sub" "change" "version" "leiningen.release/bump-version" "release"]
     ["vcs" "commit" "Pre-release version %s [skip ci]"]
     ["vcs" "tag"]
     ["vcs" "push"]]}}

  :source-paths []
  :test-paths []
  :resource-paths []

  :codox
  {:namespaces  [#"^spec\.definition\."]
   :metadata    {:doc/format :markdown}
   :output-path "docs"
   :doc-paths   ["docs"]
   :source-uri  "https://github.com/logicblocks/spec.definition/blob/{version}/{filepath}#L{line}"}

  :aliases {"install"
            ["do"
             ["sub"
              "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid"
              "install"]
             ["install"]]

            "eastwood"
            ["sub"
             "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid"
             "eastwood"]

            "cljfmt"
            ["sub"
             "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid"
             "cljfmt"]

            "kibit"
            ["sub"
             "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid"
             "kibit"]

            "check"
            ["sub"
             "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid"
             "check"]

            "clean"
            ["sub"
             "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid"
             "clean"]

            "bikeshed"
            ["sub"
             "-s" "core:string:address:bool:collection:currency:email:number:phone:time:uri:uuid"
             "bikeshed"]})
