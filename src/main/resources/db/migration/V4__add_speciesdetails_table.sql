-- CREATE NEW TABLE FOR EXTRA SPECIES DETAILS (NOT IN API)

CREATE TABLE species_extra_details (
                                     id bigserial PRIMARY KEY,
                                     species_index text,
                                     species_name text,
                                     species_tagline text,
                                     species_flavour text,
                                     species_description text,
                                     species_image text DEFAULT NULL
);

INSERT INTO species_extra_details (species_index, species_name, species_tagline, species_flavour, species_description)
VALUES
    ('dragonborn', 'Dragonborn', 'A proud race that values clan and skills above all else. Once enslaved by dragons, they strive to be self-sufficient, not wanting to be beholden to anyone, not even the gods.', 'Born of dragons, as their name proclaims, the dragonborn walk proudly through a world that greets them with fearful incomprehension. Shaped by draconic gods or the dragons themselves, dragonborn originally hatched from dragon eggs as a unique race, combining the best attributes of dragons and humanoids. Some dragonborn are faithful servants to true dragons, others form the ranks of soldiers in great wars, and still others find themselves adrift, with no clear calling in life.', 'Dragonborn look very much like dragons standing erect in humanoid form, though they lack wings or a tail. The first dragonborn had scales of vibrant hues matching the colors of their dragon kin, but generations of interbreeding have created a more uniform appearance. Their small, fine scales are usually brass or bronze in color, sometimes ranging to scarlet, rust, gold, or copper-green. They are tall and strongly built, often standing close to 6½ feet tall and weighing 300 pounds or more. Their hands and feet are strong, talonlike claws with three fingers and a thumb on each hand.

The blood of a particular type of dragon runs very strong through some dragonborn clans. These dragonborn often boast scales that more closely match those of their dragon ancestor — bright red, green, blue, or white, lustrous black, or gleaming metallic gold, silver, brass, copper, or bronze.'),
    ('dwarf', 'Dwarf', 'As durable and unyielding as their homes of stone, dwarves are some of the finest warriors, miners, and smiths of Faerûn.', 'Kingdoms rich in ancient grandeur, halls carved into the roots of mountains, the echoing of picks and hammers in deep mines and blazing forges, a commitment to clan and tradition, and a burning hatred of goblins and orcs — these common threads unite all dwarves.', 'Dwarves are solid and enduring like the mountains they love, weathering the passage of centuries with stoic endurance and little change. They respect the traditions of their clans, tracing their ancestry back to the founding of their most ancient strongholds in the youth of the world, and don’t abandon those traditions lightly. Part of those traditions is devotion to the gods of the dwarves, who uphold the dwarven ideals of industrious labor, skill in battle, and devotion to the forge.

Individual dwarves are determined and loyal, true to their word and decisive in action, sometimes to the point of stubbornness. Many dwarves have a strong sense of justice, and they are slow to forget wrongs they have suffered. A wrong done to one dwarf is a wrong done to the dwarf’s entire clan, so what begins as one dwarf’s hunt for vengeance can become a full-blown clan feud.'),
    ('elf', 'Elf', 'With ethereal countenances and long lifespans, elves are at home with nature''s power, flourishing in light and dark alike.', 'Elves are a magical people of otherworldly grace, living in the world but not entirely part of it. They live in places of ethereal beauty, in the midst of ancient forests or in silvery spires glittering with faerie light, where soft music drifts through the air and gentle fragrances waft on the breeze. Elves love nature and magic, art and artistry, music and poetry, and the good things of the world.', 'Elves can live well over 700 years, giving them a broad perspective on events that might trouble the shorter-lived races more deeply. They are more often amused than excited, and more likely to be curious than greedy. They tend to remain aloof and unfazed by petty happenstance. When pursuing a goal, however, whether adventuring on a mission or learning a new skill or art, elves can be focused and relentless. They are slow to make friends and enemies, and even slower to forget them. They reply to petty insults with disdain and to serious insults with vengeance.

Like the branches of a young tree, elves are flexible in the face of danger. They trust in diplomacy and compromise to resolve differences before they escalate to violence. They have been known to retreat from intrusions into their woodland homes, confident that they can simply wait the invaders out. But when the need arises, elves reveal a stern martial side, demonstrating skill with sword, bow, and strategy.'),
    ('gnome', 'Gnome', 'Small, clever, and energetic, gnomes use their long lives to explore Faerûn''s brightest corners and darkest depths.', 'A constant hum of busy activity pervades the warrens and neighborhoods where gnomes form their close-knit communities. Louder sounds punctuate the hum: a crunch of grinding gears here, a minor explosion there, a yelp of surprise or triumph, and especially bursts of laughter. Gnomes take delight in life, enjoying every moment of invention, exploration, investigation, creation, and play.', 'As far as gnomes are concerned, being alive is a wonderful thing, and they squeeze every ounce of enjoyment out of their three to five centuries of life. Humans might wonder about getting bored over the course of such a long life, and elves take plenty of time to savor the beauties of the world in their long years, but gnomes seem to worry that even with all that time, they can’t get in enough of the things they want to do and see.

Gnomes speak as if they can’t get the thoughts out of their heads fast enough. Even as they offer ideas and opinions on a range of subjects, they still manage to listen carefully to others, adding the appropriate exclamations of surprise and appreciation along the way.'),
    ('half-elf', 'Half-Elf', 'Curious, ambitious, and versatile, half-elves are welcome everywhere, but struggle without a community to call their own.', 'Walking in two worlds but truly belonging to neither, half-elves combine what some say are the best qualities of their elf and human parents: human curiosity, inventiveness, and ambition tempered by the refined senses, love of nature, and artistic tastes of the elves.', 'Half-elves have no lands of their own, though they are welcome in human cities and somewhat less welcome in elven forests. In large cities in regions where elves and humans interact often, half-elves are sometimes numerous enough to form small communities of their own. They enjoy the company of other half-elves, the only people who truly understand what it is to live between these two worlds.

In most parts of the world, though, half-elves are uncommon enough that one might live for years without meeting another. Some half-elves prefer to avoid company altogether, wandering the wilds as trappers, foresters, hunters, or adventurers and visiting civilization only rarely. Like elves, they are driven by the wanderlust that comes of their longevity. Others, in contrast, throw themselves into the thick of society, putting their charisma and social skills to great use in diplomatic roles or as swindlers.'),
    ('half-orc', 'Half-Orc', 'Creatures of intense emotion, half-orcs are more inclined to act than contemplate - whether the rage burning their bodies compels them to fight, or the love filling their hearts inspires acts of incredible kindness.', 'Whether united under the leadership of a mighty warlock or having fought to a standstill after years of conflict, orc and human communities, sometimes form alliances. When these alliances are sealed by marriages, half-orcs are born. Some half-orcs rise to become proud leaders of orc communities. Some venture into the world to prove their worth. Many of these become adventurers, achieving greatness for their mighty deeds.', 'The one-eyed god Gruumsh—lord of war and fury—created the first orcs, and even those orcs who turn away from his worship carry his blessings of might and endurance. The same is true of half-orcs. Some half-orcs hear the whispers of Gruumsh in their dreams, calling them to unleash the rage that simmers within them. Others feel Gruumsh’s exultation when they join in melee combat — and either exult along with him or shiver with fear and loathing.

Beyond the rage of Gruumsh, half-orcs feel emotion powerfully. Rage doesn’t just quicken their pulse, it makes their bodies burn. An insult stings like acid, and sadness saps their strength. But they laugh loudly and heartily, and simple pleasures — feasting, drinking, wrestling, drumming, and wild dancing — fill their hearts with joy. They tend to be short-tempered and sometimes sullen, more inclined to action than contemplation and to fighting than arguing. And when their hearts swell with love, they leap to perform acts of great kindness and compassion.'),
    ('halfling', 'Halfling', 'Small yet capable, halflings prefer the comforts of home and hearth - but their natural luck and dexterity makes them fine adventurers.', 'The comforts of home are the goals of most halflings’ lives: a place to settle in peace and quiet, far from marauding monsters and clashing armies; a blazing fire and a generous meal; fine drink and fine conversation. Though some halflings live out their days in remote agricultural communities, others form nomadic bands that travel constantly, lured by the open road and the wide horizon to discover the wonders of new lands and peoples.', 'Halflings are an affable and cheerful people. They cherish the bonds of family and friendship as well as the comforts of hearth and home, harboring few dreams of gold or glory. Even adventurers among them usually venture into the world for reasons of community, friendship, wanderlust, or curiosity. They love discovering new things, even simple things, such as an exotic food or an unfamiliar style of clothing.

Halflings are easily moved to pity and hate to see any living thing suffer. They are generous, happily sharing what they have even in lean times.'),
    ('human', 'Human', 'The most common face in Faerûn, humans are known for their tenacity, creativity, and endless capacity for growth.', 'In the reckonings of most worlds, humans are the youngest of the common races, late to arrive on the world scene and short-lived in comparison to dwarves, elves, and dragons. Perhaps it is because of their shorter lives that they strive to achieve as much as they can in the years they are given. Or maybe they feel they have something to prove to the elder races, and that’s why they build their mighty empires on the foundation of conquest and trade. Whatever drives them, humans are the innovators, the achievers, and the pioneers of the worlds.', 'Humans are the most adaptable and ambitious people among the common races. They have widely varying tastes, morals, and customs in the many different lands where they have settled. When they settle, though, they stay: they build cities to last for the ages, and great kingdoms that can persist for long centuries. An individual human might have a relatively short life span, but a human nation or culture preserves traditions with origins far beyond the reach of any single human’s memory. They live fully in the present — making them well suited to the adventuring life — but also plan for the future, striving to leave a lasting legacy. Individually and as a group, humans are adaptable opportunists, and they stay alert to changing political and social dynamics.'),
    ('tiefling', 'Tiefling', 'Descended from devils of the Nine Hells, tieflings face constant suspicion in Faerûn. Thankfully, their arcane abilities make them natural survivors.', 'To be greeted with stares and whispers, to suffer violence and insult on the street, to see mistrust and fear in every eye: this is the lot of the tiefling. And to twist the knife, tieflings know that this is because a pact struck generations ago infused the essence of Asmodeus — overlord of the Nine Hells — into their bloodline. Their appearance and their nature are not their fault but the result of an ancient sin, for which they and their children and their children’s children will always be held accountable.', 'Tieflings are derived from human bloodlines, and in the broadest possible sense, they still look human. However, their infernal heritage has left a clear imprint on their appearance. Tieflings have large horns that take any of a variety of shapes: some have curling horns like a ram, others have straight and tall horns like a gazelle’s, and some spiral upward like an antelopes’ horns. They have thick tails, four to five feet long, which lash or coil around their legs when they get upset or nervous.
Tieflings subsist in small minorities found mostly in human cities or towns, often in the roughest quarters of those places, where they grow up to be swindlers, thieves, or crime lords. Sometimes they live among other minority populations in enclaves where they are treated with more respect.

Lacking a homeland, tieflings know that they have to make their own way in the world and that they have to be strong to survive. They are not quick to trust anyone who claims to be a friend, but when a tiefling’s companions demonstrate that they trust him or her, the tiefling learns to extend the same trust to them. And once a tiefling gives someone loyalty, the tiefling is a firm friend or ally for life.');