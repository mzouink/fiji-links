1- Split the uri into plugin, path and query.

Query example:`fiji:openBDV/subpath?foo=bar+address=place`

"fiji://openBDV/s3://janelia-cosem-datasets/jrc_mus-hippocampus-1/jrc_mus-hippocampus-1.n5?foo=bar+address=place";
fiji:openBDV/s3://janelia-cosem-datasets/jrc_mus-hippocampus-1/jrc_mus-hippocampus-1.n5?foo=bar+address=place


fiji://openBDV/dum_path?path=s3%3A%2F%2Fjanelia-cosem-datasets%2Fjrc_mus-hippocampus-1%2Fjrc_mus-hippocampus-1.n5+dataset=em%2Ffibsem-uint8%2Fs5

2- Create plugin type decorator URLOperation
