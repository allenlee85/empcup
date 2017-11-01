(ns empcup.game)

(def cards
  [{:card "K"
    :name "KING"
    :rule [:p
           [:ul
            [:li "Upon drawing the king you shall now be referred to as my lord/lady, my king/queen, my emperor/empress."]
            [:li "You will pour into the chalice and decree a new law."]
            [:li "Emperor shall wear the crown and can request anyone to fill their glass."]
            [:li "The fourth king is the fall of the empire and must drink the sins of all previous emperors and signifies the end of the game."]]]}
   
   {:card "Q"
    :name "QUEEN"
    :rule [:p
           [:ul
            [:li "Spreading Rumors... The queen will ask a personal question in regards to the group to the person on the right. The person to right names the person to the queen's question out loud. Anyone who wants to know what the queen asked must pay the price of a drink."]
            [:li "Power of the Final Word, settles any disputes in regards to punishments, arguments, and contests. (If no queen has been drawn disputes are settled by vote, but as soon as it is drawn the queen has the final word... why? Cus this shit ain't no democracy. It's Emperor's Cup.)"]]]}

   {:card "J"
    :name "JACK"
    :rule [:p
           [:ul
            [:li "Upon drawing the Jack you are the Thumb-master. Any time you place your thumb on the table everyone must follow suit. Last person to place their thumb on table loses and must drink."]]]}

   {:card "10"
    :name "10"    
    :rule [:p
           [:ul
            [:li "Waterfalls"]]]}

   {:card "9"
    :name "9"
    :rule [:p
           [:ul
            [:li "Questions, point followed by question."]]]}

   {:card "8"
    :name "8"
    :rule [:p
           [:ul
            [:li ".08 rule, how drunk are you, person who selects the card selects someone else to perform a field sobriety test of their choosing, if they pass person holding card drinks. If they fail they drink. If they refuse to take the test, punishment die."]]]}

   {:card "7"
    :name "7"    
    :rule [:p
           [:ul
            [:li "Dare: Select someone whom you wish to dare, if dare is completed person who drew the card drinks. If person fails or refuses to do dare then they must roll the punishment die."]]]}

   {:card "6"
    :name "6"
    :rule [:p
           [:ul
            [:li "Flip Cup Challenge: Select a fellow team captain, select teammates one at a time for a 3 vs 3 challenge. Losers must drink."]]]}   

   {:card "5"
    :name "5"
    :rule [:p
           [:ul
            [:li "Never Have I Ever"]]]}   

   {:card "4"
    :name "4"
    :rule [:p
           [:ul
            [:li "Quarters Challenge: Pick a challenger and then each of you picks a teammate. You face off a game of quarters. Losers must drink."]]]}

   {:card "3"
    :name "3"
    :rule [:p
           [:ul
            [:li "Taboo: choose a word that cannot be used for the remainder of the game. Saying the word results in a drink."]]]}

   {:card "2"
    :name "2"
    :rule [:p
           [:ul
            [:li "Slave Card: You are now the slave of the game, it's not as bad as it sounds, you're just required to get everyone their beverages. Refusing to do so will result in your punishment."]]]}
   
   {:card "A"
    :name "ACE"
    :rule [:p
           [:ul
            [:li "The Ace up your sleeve means you're granted immunity from any punishment one time or declare sanctuary from the jaws of defeat in any contest."]]]}

   
   ])

(def card-index
  (into {} (map vector
                (for [{:keys [card]} cards] card)
                (range 0 (count cards)))))

(defn get-card [card]
  (cards (card-index card)))

(def punishments
  ["You're not worth the clothes on your back"
   "You Suck"
   "Take a Shot: Take a shot of alcohol your choice or down your drink"
   "Bad kitty"
   "Put baby in the corner: Go stand in the corner until your next turn"
   "Trolling: Hang out underneath the table until your next 3 turns"
   ])

