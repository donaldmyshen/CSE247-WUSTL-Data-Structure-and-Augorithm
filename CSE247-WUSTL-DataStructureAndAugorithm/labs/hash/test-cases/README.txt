README FOR TEST CASES

Here are some test cases on which to exercise your hash table code.

** CASE 1

These are some very short sequences that I made up just to help you
test your implementation.

** CASE 2

These sequences contain the human and mouse genes for preproinsulin, the
protein that is processed into the insulin hormone that regulates blood
sugar.  I used a match length of 16 to avoid getting too many short matches
by chance alone.

From your output, can you guess approximately which parts of the two
sequences encode the gene?  Hint: sequences that encode a common gene
tend to differ less than sequences that do not.  Also, a gene can
occur in multiple pieces (called "exons") with gaps in between.

** CASE 3

These sequences come from the mnd2 locus.  The mnd2 gene encodes a
protein important to correct functioning of the nerves that carry
impulses from the brain to the muscles.  In mice, mutations in this
gene cause "motor neuron degeneration 2" syndrome, which is closely
related to certain kinds of muscular dystrophy in humans.
 
The two sequences in this case come from the human and mouse genomes.
There is no mask sequence.  I used a match length of 18 to avoid
getting too many short matches by chance alone.

** CASE 4

These sequences come from the human and mouse HOX-D loci. HOX genes
are essential to early development; they control important stuff like
which bits of your body have arms and legs attached.  In fruit flies,
a mutation in one of these genes produces a fly that sprouts legs on
its head, where its anntenae should be!
 
I've removed the common repeats in these sequences with a program
called RepeatMasker, which replaces them with n's.  There is a mask
sequence that filters out most (sadly, not all) of the matches between
these n's.  This locus contains regions of very high similarity, so to
avoid generating huge amounts of output, I used a match length of 180.
