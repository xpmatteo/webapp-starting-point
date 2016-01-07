
export LANG=C
TEX=pdflatex
OUTPUT_DIR=/tmp/pdflatex

%.eps : %.dot
	dot -Tps -o $@ $<

%.pdf: %.tex lib/preamble-beamer.tex
	@mkdir $(OUTPUT_DIR) 2> /dev/null || true
	pdflatex -output-directory=$(OUTPUT_DIR) $<
	cp $(OUTPUT_DIR)/$@ .

all: todolist.pdf

clean:
	rm -rf -- *~ *.log *.aux *.dvi *.ps *.nav *.out *.snm *.toc *.vrb tmp.* $(OUTPUT_DIR)


# EOF