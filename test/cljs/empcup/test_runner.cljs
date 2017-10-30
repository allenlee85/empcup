(ns empcup.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [empcup.core-test]
   [empcup.common-test]))

(enable-console-print!)

(doo-tests 'empcup.core-test
           'empcup.common-test)
